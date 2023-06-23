package com.delazeri.music.albums.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.delazeri.music.albums.dtos.AlbumDTO;
import com.delazeri.music.albums.dtos.SimpleAlbumDTO;
import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.albums.repositories.AlbumRepository;
import com.delazeri.music.albums.utils.AlbumMapper;
import com.delazeri.music.artists.dtos.ArtistDTO;
import com.delazeri.music.artists.entities.Artist;
import com.delazeri.music.reviews.dtos.ReviewDTO;
import com.delazeri.music.reviews.entities.Review;
import com.delazeri.music.reviews.repositories.ReviewRepository;
import com.delazeri.music.security.jwt.JwtUtil;
import com.delazeri.music.users.entities.User;
import com.delazeri.music.users.repositories.UserRepository;
import com.delazeri.music.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository repository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AlbumMapper mapper;

    public Page<SimpleAlbumDTO> findAll(Pageable page) {
        Page<SimpleAlbumDTO> albums = repository.findAllAlbums(page);

        albums.forEach(
                albumDTO -> {
                    Set<Artist> artists = repository.findAlbumArtists(albumDTO.getId());

                    albumDTO.setArtists(new HashSet<>(ModelMapper.parseListObjects(Arrays.asList(artists.toArray()), ArtistDTO.class)));
                }
        );

        return albums;
    }

    public AlbumDTO findById(UUID id) {
        return mapper.entityToDto(repository.findById(id).orElseThrow());
    }

    public AlbumDTO findByIdWithUserInformation(UUID id, String authenticationToken) {
        DecodedJWT decodedJWT = jwtUtil.decodedToken(authenticationToken);
        User user = userRepository.findByUsername(decodedJWT.getSubject());
        Album album = repository.findById(id).orElseThrow();

        AlbumDTO albumDTO = mapper.entityToDto(album            );

        Review review = reviewRepository.findByAlbumAndUser(album, user);

        if (review != null) {
           albumDTO.setUserReview(ModelMapper.parseObject(review, ReviewDTO.class));
        }

        return albumDTO;
    }

    public AlbumDTO findBySlug(String slug) {
        return mapper.entityToDto(repository.findBySlug(slug).orElseThrow());
    }

    public Page<SimpleAlbumDTO> findAllReviewdAlbumsOrderedByPopularity(Pageable pageable) {
        Page<SimpleAlbumDTO> albums = repository.findAllReviewdAlbumsOrderedByPopularity(pageable, LocalDateTime.now().minusDays(7));

        albums.forEach(
                albumDTO -> {
                    Set<Artist> artists = repository.findAlbumArtists(albumDTO.getId());

                    albumDTO.setArtists(new HashSet<>(ModelMapper.parseListObjects(Arrays.asList(artists.toArray()), ArtistDTO.class)));
                }
        );

        return albums;
    }
}
