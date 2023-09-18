package com.delazeri.music.controllers;

import com.delazeri.music.dtos.reviews.ReviewDTO;
import com.delazeri.music.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reviews")
public class ReviewController {

    @Autowired
    ReviewService service;

    @GetMapping
    public ResponseEntity<Page<ReviewDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @GetMapping(value = "/by-album/{albumId}")
    public ResponseEntity<List<ReviewDTO>> findReviewsByAlbum(@PathVariable String albumId) {
        return ResponseEntity.ok().body(service.findReviewsByAlbum(albumId));
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> postReview(@RequestBody ReviewDTO review, @RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok().body(service.postReview(review, token));
    }
}
