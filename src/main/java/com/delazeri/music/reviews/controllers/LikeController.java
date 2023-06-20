package com.delazeri.music.reviews.controllers;

import com.delazeri.music.reviews.dtos.ReviewDTO;
import com.delazeri.music.reviews.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/reviews/like")
public class LikeController {

//    @Autowired
//    LikeService service;
//
//    @PostMapping
//    public ResponseEntity<ReviewDTO> postLike(@RequestHeader("Authorization") String authorizationToken, UUID reviewId) {
//        return ResponseEntity.ok().body(service.postLike(authorizationToken, reviewId));
//    }
}
