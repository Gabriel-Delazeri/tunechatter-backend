package com.delazeri.music.controllers;

import com.delazeri.music.dtos.albums.AlbumDTO;
import com.delazeri.music.dtos.albums.SimpleAlbumDTO;
import com.delazeri.music.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(value = "**")
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
    public ResponseEntity<AlbumDTO> findById(@PathVariable UUID id, @RequestHeader("AUTHORIZATION") String authenticationToken) {
        return ResponseEntity.ok().body(service.findByIdWithUserInformation(id, authenticationToken));
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
