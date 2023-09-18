package com.delazeri.music.services;

import com.delazeri.music.domain.Album;
import com.delazeri.music.domain.Copyright;
import com.delazeri.music.repositories.AlbumRepository;
import com.delazeri.music.domain.spotify.api.SpotifyAlbum;
import com.delazeri.music.domain.Artist;
import com.delazeri.music.repositories.ArtistRepository;
import com.delazeri.music.domain.Track;
import com.delazeri.music.repositories.TrackRepository;
import com.delazeri.music.utils.mapper.ModelMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.slugify.Slugify;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class SpotifyAlbumService {

    @Autowired
    AlbumRepository repository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    TrackRepository trackRepository;

    @Value("${spotify.api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private final static Slugify slugify = Slugify.builder().build();

    private static HttpEntity<String> setHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", accessToken);
        return new HttpEntity<>(headers);
    }

    public Album populateDatabaseBySpotifyAlbum(String spotifyAlbumId, String accessToken) throws JsonProcessingException {
        HttpEntity<String> entity = setHeaders(accessToken);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                baseUrl + "/albums/" + spotifyAlbumId,
                HttpMethod.GET,
                entity,
                String.class
        );

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            SpotifyAlbum spotifyAlbum = objectMapper.readValue(
                    responseBody,
                    SpotifyAlbum.class
            );

            Optional<Album> existingAlbum = albumRepository.findBySpotifyId(spotifyAlbum.getSpotifyId());
            if (existingAlbum.isPresent()) {
                return existingAlbum.get();
            }

            Album album = saveAlbum(spotifyAlbum);
            saveTracks(spotifyAlbum, album);

            return albumRepository.findById(album.getId()).orElseThrow();
        }

        throw new IllegalStateException("Failed to search album on Spotify.");
    }

    public Album saveAlbum(SpotifyAlbum spotifyAlbum) {
        Album album = new Album();
        BeanUtils.copyProperties(spotifyAlbum, album);
        album.setExternalUrl(spotifyAlbum.getExternalUrl().getSpotifyUrl());
        album.setImageUrl(spotifyAlbum.getImages().get(0).getUrl());
        album.setSlug(slugify.slugify(album.getName()));
        Set<Copyright> copyrights = new HashSet<>();

        spotifyAlbum.getCopyrights().forEach(
                copyright -> {
                    Copyright copyrightToBeAdded = ModelMapper.parseObject(copyright, Copyright.class);
                    copyrightToBeAdded.setAlbum(album);
                    copyrights.add(copyrightToBeAdded);
                }
        );

        album.setCopyrights(copyrights);
        spotifyAlbum.getArtists().forEach(artist -> {
            Optional<Artist> existingArtist = artistRepository.findBySpotifyId(artist.getSpotifyId());
            if (existingArtist.isPresent()) {
                album.getArtists()
                        .add(existingArtist.get());
            } else {
                album.getArtists()
                        .add(new Artist(artist.getSpotifyId(), artist.getExternalUrl().getSpotifyUrl(), artist.getName(), slugify.slugify(artist.getName())));
            }
        });

        return albumRepository.save(album);
    }

    public void saveTracks(SpotifyAlbum spotifyAlbum, Album album) {
        List<Artist> currentDatabaseArtists = artistRepository.findAll();
        List<Track> tracks = new ArrayList<>();

        Set<String> existingArtistIds = new HashSet<>();
        for (Artist existingArtist : currentDatabaseArtists) {
            existingArtistIds.add(existingArtist.getSpotifyId());
        }

        spotifyAlbum.getTrackList().getItems().forEach(track -> {
            Set<Artist> artists = new HashSet<>();

            track.getArtists().forEach(artist -> {
                if (existingArtistIds.contains(artist.getSpotifyId())) {
                    Optional<Artist> existingArtist = artistRepository.findBySpotifyId(artist.getSpotifyId());
                    existingArtist.ifPresent(artists::add);
                } else {
                    artists.add(new Artist(artist.getSpotifyId(), artist.getExternalUrl().getSpotifyUrl(), artist.getName(), slugify.slugify(artist.getName())));
                    existingArtistIds.add(artist.getSpotifyId());
                }
            });

            tracks.add(new Track(
                    track.getSpotifyId(),
                    track.getName(),
                    track.getTrackNumber(),
                    slugify.slugify(track.getName()),
                    artists,
                    track.getDurationMs(),
                    track.getExternalUrl().getSpotifyUrl(),
                    album.getImageUrl(),
                    album
            ));
        });

        trackRepository.saveAll(tracks);
    }
}
