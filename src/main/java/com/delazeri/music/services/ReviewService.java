package com.delazeri.music.services;

import com.delazeri.music.domain.Album;
import com.delazeri.music.utils.mapper.AlbumMapper;
import com.delazeri.music.infra.exceptions.custom.UserAlreadyReviewedThisAlbumException;
import com.delazeri.music.dtos.reviews.ReviewDTO;
import com.delazeri.music.domain.Review;
import com.delazeri.music.repositories.ReviewRepository;
//import com.delazeri.music.security.jwt.JwtUtil;
import com.delazeri.music.domain.User;
import com.delazeri.music.utils.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {

//    @Autowired
//    UserService userService;

    @Autowired
    AlbumService albumService;

//    @Autowired
//    JwtUtil jwtUtil;

    @Autowired
    AlbumMapper albumMapper;

    @Autowired
    ReviewRepository repository;

    public ReviewDTO postReview(ReviewDTO reviewDTO, String token) {
//        DecodedJWT decodedToken = jwtUtil.decodedToken(token);

//        User user = userService.findUserByUsername(decodedToken.getSubject());
        // todo fix
        User user = new User();
        Album album = albumMapper.dtoToEntity(albumService.findById(reviewDTO.getAlbum().getId()));

        userAlreadyReviewedThisAlbum(album, user);

        Review review = ModelMapper.parseObject(reviewDTO, Review.class);
        review.setAlbum(album);
        review.setUser(user);
        review.setPostedAt(LocalDateTime.now());

        return ModelMapper.parseObject(repository.save(review), ReviewDTO.class);
    }

    public void userAlreadyReviewedThisAlbum(Album album, User user) {
        Review review = repository.findByAlbumAndUser(album, user);

        if (review != null) {
            throw new UserAlreadyReviewedThisAlbumException("You already reviewed this album");
        }
    }

    public List<ReviewDTO> findReviewsByAlbum(String albumId) {
        Album album = albumMapper.dtoToEntity(albumService.findById(UUID.fromString(albumId)));

        return ModelMapper.parseListObjects(repository.findByAlbum(album), ReviewDTO.class);
    }

    public Page<ReviewDTO> findAll(Pageable pageable) {
        return ModelMapper.parsePage(repository.findAllReviews(pageable), ReviewDTO.class);
    }
}
