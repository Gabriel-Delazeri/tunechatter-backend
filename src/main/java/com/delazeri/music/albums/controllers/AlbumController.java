package com.delazeri.music.albums.controllers;

import com.delazeri.music.albums.dtos.AlbumDTO;
import com.delazeri.music.albums.dtos.SimpleAlbumDTO;
import com.delazeri.music.albums.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    AlbumService service;

    @GetMapping
    public ResponseEntity<List<SimpleAlbumDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity<AlbumDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("by-slug/{slug}")
    public ResponseEntity<AlbumDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok().body(service.findBySlug(slug));
    }
}
