package com.delazeri.music.albums.controllers;

import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.albums.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("albums")
public class AlbumController {

    @Autowired
    AlbumService service;

    @GetMapping
    public ResponseEntity<List<Album>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Album> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }
}
