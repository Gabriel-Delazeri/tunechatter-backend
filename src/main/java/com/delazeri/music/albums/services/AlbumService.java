package com.delazeri.music.albums.services;

import com.delazeri.music.albums.dtos.AlbumDTO;
import com.delazeri.music.albums.dtos.SimpleAlbumDTO;
import com.delazeri.music.albums.repositories.AlbumRepository;
import com.delazeri.music.albums.utils.AlbumMapper;
import com.delazeri.music.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository repository;

    @Autowired
    AlbumMapper mapper;

    public List<SimpleAlbumDTO> findAll() {
        List<SimpleAlbumDTO> albums = repository.findAllAlbums();

        albums.forEach(
                albumDTO -> {albumDTO.setArtists(repository.findAlbumArtists(albumDTO.getId()));}
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
