package com.delazeri.music.albums.services;

import com.delazeri.music.albums.dtos.AlbumDTO;
import com.delazeri.music.albums.dtos.SimpleAlbumDTO;
import com.delazeri.music.albums.repositories.AlbumRepository;
import com.delazeri.music.albums.utils.AlbumMapper;
import com.delazeri.music.artists.dtos.ArtistDTO;
import com.delazeri.music.artists.entities.Artist;
import com.delazeri.music.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository repository;

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

    public AlbumDTO findBySlug(String slug) {
        return mapper.entityToDto(repository.findBySlug(slug).orElseThrow());
    }
}
