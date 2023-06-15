package com.delazeri.music.reviews.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.delazeri.music.albums.entities.Album;
import com.delazeri.music.albums.services.AlbumService;
import com.delazeri.music.albums.utils.AlbumMapper;
import com.delazeri.music.exceptions.custom.UserAlreadyReviewedThisAlbumException;
import com.delazeri.music.reviews.dtos.ReviewDTO;
import com.delazeri.music.reviews.entities.Review;
import com.delazeri.music.reviews.repositories.ReviewRepository;
import com.delazeri.music.security.jwt.JwtUtil;
import com.delazeri.music.users.entities.User;
import com.delazeri.music.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

    @Autowired
    UserService userService;

    @Autowired
    AlbumService albumService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AlbumMapper albumMapper;

    @Autowired
    ReviewRepository repository;

    public ReviewDTO postReview(ReviewDTO reviewDTO, String token) {
        DecodedJWT decodedToken = jwtUtil.decodedToken(token);

        User user = userService.findUserByUsername(decodedToken.getSubject());
        Album album = albumMapper.dtoToEntity(albumService.findById(UUID.fromString(reviewDTO.getAlbumID())));

        userAlreadyReviewedThisAlbum(album, user);

        Review review = new Review();
        review.setAlbum(album);
        review.setUser(user);
        review.setComment(reviewDTO.getComment());
        repository.save(review);

        return reviewDTO;
    }

    public void userAlreadyReviewedThisAlbum(Album album, User user) {
        Review review = repository.findByAlbumAndUser(album, user);

        if (review != null) {
            throw new UserAlreadyReviewedThisAlbumException("You already reviewed this album");
        }
    }
}
