package com.delazeri.music.utils.mapper;

import com.delazeri.music.dtos.albums.AlbumDTO;
import com.delazeri.music.dtos.albums.CopyrightDTO;
import com.delazeri.music.domain.Album;
import com.delazeri.music.domain.Copyright;
import com.delazeri.music.dtos.artists.ArtistDTO;
import com.delazeri.music.domain.Artist;
import com.delazeri.music.dtos.tracks.TrackDTO;
import com.delazeri.music.domain.Track;
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
