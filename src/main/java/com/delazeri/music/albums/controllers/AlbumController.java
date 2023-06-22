package com.delazeri.music.albums.controllers;

import com.delazeri.music.albums.dtos.AlbumDTO;
import com.delazeri.music.albums.dtos.SimpleAlbumDTO;
import com.delazeri.music.albums.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    AlbumService service;

    @GetMapping
    public ResponseEntity<Page<SimpleAlbumDTO>> findAll(Pageable page) {
        return ResponseEntity.ok().body(service.findAll(page));
    }

    @GetMapping("by-id/{id}")
    public ResponseEntity<AlbumDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping("by-slug/{slug}")
    public ResponseEntity<AlbumDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok().body(service.findBySlug(slug));
    }

    @GetMapping(value = "/popular")
    public ResponseEntity<Page<SimpleAlbumDTO>> findAllReviewedAlbumsOrderedByPopularity(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAllReviewdAlbumsOrderedByPopularity(pageable));
    }
}
