package com.delazeri.music.reviews.controllers;

import com.delazeri.music.reviews.dtos.ReviewDTO;
import com.delazeri.music.reviews.services.LikeService;
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
}
