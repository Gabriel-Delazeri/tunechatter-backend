package com.delazeri.music.albums.controllers;

import com.delazeri.music.albums.dtos.AlbumDTO;
import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.albums.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("albums")
public class AlbumController {

    @Autowired
    AlbumService service;

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity<Album> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("by-slug/{slug}")
    public ResponseEntity<Album> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok().body(service.findBySlug(slug));
    }
}
