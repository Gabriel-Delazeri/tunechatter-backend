package com.delazeri.music.reviews.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.delazeri.music.exceptions.custom.UserAlreadyLikedThisReviewException;
import com.delazeri.music.exceptions.custom.UserStillNotLikedReviewException;
import com.delazeri.music.reviews.dtos.ReviewDTO;
import com.delazeri.music.reviews.entities.Like;
import com.delazeri.music.reviews.entities.Review;
import com.delazeri.music.reviews.repositories.LikeRepository;
import com.delazeri.music.reviews.repositories.ReviewRepository;
import com.delazeri.music.security.jwt.JwtUtil;
import com.delazeri.music.users.entities.User;
import com.delazeri.music.users.services.UserService;
import com.delazeri.music.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LikeService {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    LikeRepository repository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserService userService;

    public ReviewDTO postLike(String authorizationToken , UUID reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        DecodedJWT decodedToken = jwtUtil.decodedToken(authorizationToken);

        User user = userService.findUserByUsername(decodedToken.getSubject());
        userAlreadyLikedThisReview(user, review);

        repository.save(new Like(user, review));

        return ModelMapper.parseObject(review, ReviewDTO.class);
    }

    public void userAlreadyLikedThisReview(User user, Review review) {
        Like like = repository.findByUserAndReview(user, review);

        if (like != null) {
            throw new UserAlreadyLikedThisReviewException("You already liked this review");
        }
    }

    public ReviewDTO unlike(String authorizationToken, UUID reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        DecodedJWT decodedToken = jwtUtil.decodedToken(authorizationToken);

        User user = userService.findUserByUsername(decodedToken.getSubject());

        repository.delete(getUserLikeInReview(user, review));

        return ModelMapper.parseObject(review, ReviewDTO.class);
    }

    public Like getUserLikeInReview(User user, Review review) {
        Like like = repository.findByUserAndReview(user, review);

        if (like == null) {
            throw new UserStillNotLikedReviewException("You still not liked this review");
        }

        return like;
    }
}
