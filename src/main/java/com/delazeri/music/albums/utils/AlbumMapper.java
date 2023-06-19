package com.delazeri.music.albums.utils;

import com.delazeri.music.albums.dtos.AlbumDTO;
import com.delazeri.music.albums.dtos.CopyrightDTO;
import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.albums.entities.Copyright;
import com.delazeri.music.artists.dtos.ArtistDTO;
import com.delazeri.music.artists.entities.Artist;
import com.delazeri.music.tracks.dtos.TrackDTO;
import com.delazeri.music.tracks.entities.Track;
import com.delazeri.music.utils.mapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class AlbumMapper {

    public AlbumDTO entityToDto(Album album) {
        AlbumDTO albumDTO = ModelMapper.parseObject(album, AlbumDTO.class);

        album.getArtists().forEach(artist -> {
            albumDTO.getArtists().add(ModelMapper.parseObject(artist, ArtistDTO.class));
        });

        album.getTracks().forEach(track -> {
            albumDTO.getTracks().add(ModelMapper.parseObject(track, TrackDTO.class));
        });

        albumDTO.setCopyrights(new HashSet<>(ModelMapper.parseListObjects(Arrays.asList(album.getCopyrights().toArray()), CopyrightDTO.class)));

        return albumDTO;
    }

    public Album dtoToEntity(AlbumDTO albumDTO) {
        Album album = ModelMapper.parseObject(albumDTO, Album.class);

        albumDTO.getArtists().forEach(artist -> {
            album.getArtists().add(ModelMapper.parseObject(artist, Artist.class));
        });

        albumDTO.getTracks().forEach(track -> {
            album.getTracks().add(ModelMapper.parseObject(track, Track.class));
        });

        album.setCopyrights(new HashSet<>(ModelMapper.parseListObjects(Arrays.asList(albumDTO.getCopyrights().toArray()), Copyright.class)));

        return album;
    }

}
