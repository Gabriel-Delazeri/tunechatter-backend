package com.delazeri.music.controllers;

import com.delazeri.music.dtos.reviews.ReviewDTO;
import com.delazeri.music.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/reviews")
public class LikeController {

    @Autowired
    LikeService service;

    @PostMapping(value = "{reviewId}/like")
    public ResponseEntity<ReviewDTO> postLike(@RequestHeader("Authorization") String authorizationToken, @PathVariable UUID reviewId) {
        return ResponseEntity.ok().body(service.postLike(authorizationToken, reviewId));
    }

    @PostMapping(value = "{reviewId}/unlike")
    public ResponseEntity<ReviewDTO> unlike(@RequestHeader("Authorization") String authorizationToken, @PathVariable UUID reviewId) {
        return ResponseEntity.ok().body(service.unlike(authorizationToken, reviewId));
    }
}
