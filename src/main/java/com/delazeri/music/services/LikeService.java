package com.delazeri.music.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.delazeri.music.infra.exceptions.custom.UserStillNotLikedReviewException;
import com.delazeri.music.dtos.reviews.ReviewDTO;
import com.delazeri.music.domain.Like;
import com.delazeri.music.domain.Review;
import com.delazeri.music.infra.security.TokenService;
import com.delazeri.music.repositories.LikeRepository;
import com.delazeri.music.repositories.ReviewRepository;
import com.delazeri.music.domain.User;
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
    UserService userService;

    @Autowired
    TokenService tokenService;

    public ReviewDTO postLike(String authorizationToken , UUID reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        DecodedJWT decodedToken = tokenService.decodeToken(authorizationToken);

        User user = userService.getUserByUsername(decodedToken.getSubject());

        if (userAlreadyLikedThisReview(user, review)) {
            return ModelMapper.parseObject(reviewRepository.findById(reviewId), ReviewDTO.class);
        }

        repository.save(new Like(user, review));

        return ModelMapper.parseObject(reviewRepository.findById(reviewId), ReviewDTO.class);
    }

    public Boolean userAlreadyLikedThisReview(User user, Review review) {
        Like like = repository.findByUserAndReview(user, review);

        if (like != null) {
            repository.delete(getUserLikeInReview(user, review));
            return true;
        }

        return false;
    }

    public ReviewDTO unlike(String authorizationToken, UUID reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow();
        // todo fix
        DecodedJWT decodedToken = tokenService.decodeToken(authorizationToken);

        User user = userService.getUserByUsername(decodedToken.getSubject());

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
