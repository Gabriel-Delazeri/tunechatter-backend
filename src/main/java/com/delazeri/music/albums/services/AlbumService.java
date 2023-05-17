package com.delazeri.music.albums.services;

import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.albums.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository repository;

    public List<Album> findAll() {
        return repository.findAll();
    }

    public Album findById(UUID id) {
        return repository.findById(id).orElseThrow();
    }
}
