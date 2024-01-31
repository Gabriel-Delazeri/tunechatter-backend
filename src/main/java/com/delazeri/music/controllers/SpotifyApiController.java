package com.delazeri.music.controllers;

import com.delazeri.music.services.SpotifyAlbumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("populate")
public class SpotifyApiController {

    @Autowired
    SpotifyAlbumService spotifyApiService;

    @GetMapping("by-album")
    public ResponseEntity<String> populateByAlbum(@RequestHeader("SpotifyToken") String accessToken) throws JsonProcessingException {
        String[] albumsIds = {"35UJLpClj5EDrhpNIi4DFg", "79dL7FLiJFOO0EoehUHQBv", "4rJgzzfFHAVFhCSt2P4I3j", "2nkto6YNI4rUYTLqEwWJ3o",
              "7GjVWG39IOj4viyWplJV4H", "6mm1Skz3JE6AXneya9Nyiv", "2UJcKiJxNryhL050F5Z1Fk", "4m2880jivSbbyEGAKfITCa", "4mAhdh996uW5SnnFKXUmC0", "6dVIqQ8qmQ5GBnJ9shOYGE", "3gBVdu4a1MMJVMy6vwPEb8",
                "35UJLpClj5EDrhpNIi4DFg", "4kkVGtCqE2NiAKosri9Rnd", "64LU4c1nfjz1t4VnGhagcg", "2Ek1q2haOnxVqhvVKqMvJe", "4SZko61aMnmgvNhfhgTuD3", "31qVWUdRrlb8thMvts0yYL", "1NAmidJlEaVgA3MpcPFYGq"
        };

        for (String albumId : albumsIds) {
            this.spotifyApiService.populateDatabaseBySpotifyAlbum(albumId, accessToken);
        }

        return ResponseEntity.ok().body("Worked");
    }
}
