package com.delazeri.music.albums.controllers;

import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.albums.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
