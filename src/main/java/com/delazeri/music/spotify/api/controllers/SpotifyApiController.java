package com.delazeri.music.spotify.api.controllers;

import com.delazeri.music.spotify.api.entities.Playlist;
import com.delazeri.music.spotify.api.services.SpotifyApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("populate")
public class SpotifyApiController {

    @Autowired
    SpotifyApiService spotifyApiService;

    @GetMapping("by-playlist/{playlistId}")
    public ResponseEntity<Playlist> populateByPlaylist(@PathVariable String playlistId, @RequestHeader("Authorization") String accessToken) throws JsonProcessingException {
        return ResponseEntity.ok().body(spotifyApiService.getPlaylistTracks(playlistId, accessToken));
    }
}
