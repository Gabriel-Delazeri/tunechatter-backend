package com.delazeri.music.albums.services;

import com.delazeri.music.albums.dtos.AlbumDTO;
import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.albums.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository repository;

    public List<AlbumDTO> findAll() {
        List<AlbumDTO> albums = repository.findAllAlbums();
        albums.forEach(
                albumDTO -> {albumDTO.setArtists(repository.findAlbumArtists(albumDTO.getId()));}
        );

        return albums;
    }

    public Album findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public Album findBySlug(String slug) {
        return repository.findBySlug(slug).orElseThrow();
    }
}
