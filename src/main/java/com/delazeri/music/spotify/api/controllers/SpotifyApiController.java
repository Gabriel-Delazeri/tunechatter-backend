package com.delazeri.music.spotify.api.controllers;

import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.spotify.api.services.SpotifyAlbumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("populate")
public class SpotifyApiController {

    @Autowired
    SpotifyAlbumService spotifyApiService;

    @GetMapping("by-album/{albumId}")
    public ResponseEntity<Album> populateByAlbum(@PathVariable String albumId, @RequestHeader("Authorization") String accessToken) throws JsonProcessingException {
        return ResponseEntity.ok().body(spotifyApiService.populateDatabaseBySpotifyAlbum(albumId, accessToken));
    }
}
