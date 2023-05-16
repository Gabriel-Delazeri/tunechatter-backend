package com.delazeri.music.albums.services;

import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.albums.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    @Autowired
    AlbumRepository repository;

    public List<Album> findAll() {
        return repository.findAll();
    }
}
