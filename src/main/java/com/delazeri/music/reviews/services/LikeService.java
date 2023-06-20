package com.delazeri.music.reviews.services;

import com.delazeri.music.reviews.dtos.ReviewDTO;
import com.delazeri.music.reviews.entities.Like;
import com.delazeri.music.reviews.entities.Review;
import com.delazeri.music.reviews.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class LikeService {

//    @Autowired
//    ReviewRepository reviewRepository;
//
//    public ReviewDTO postLike(String authorizationToken , UUID reviewId) {
//        Review review = reviewRepository.findById(reviewId).orElseThrow();
////        review.getLikes().add();
//        // validar se o usuário já deu like nesse review.
//        // postar o like nesse review.
//
//        return new ReviewDTO();
//    }
}
