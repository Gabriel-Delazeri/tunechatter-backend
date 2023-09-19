package com.delazeri.music.repositories;

import com.delazeri.music.domain.Album;
import com.delazeri.music.domain.Review;
import com.delazeri.music.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    @Query("SELECT r FROM Review as r WHERE r.album =:album AND r.user =:user")
    Review findByAlbumAndUser(@Param("album") Album album, @Param("user") User user);

    @Query(
            "SELECT new Review (r.id, r.album, r.user, r.comment, r.postedAt, r.rating, COUNT(l.id)) FROM Review r" +
                    " LEFT JOIN r.likes l" +
                    " WHERE r.album =:album " +
                    " GROUP BY r.id"
    )
    List<Review> findByAlbum(@Param("album") Album album);

    @Override
    @Query(
            "SELECT new Review (r.id, r.album, r.user, r.comment, r.postedAt, r.rating, COUNT(l.id)) FROM Review r" +
                    " LEFT JOIN r.likes l" +
                    " WHERE r.id =:id " +
                    " GROUP BY r.id"
    )
    Optional<Review> findById(@Param("id") UUID id);

    @Query("SELECT new Review (r.id, r.album, r.user, r.comment, r.postedAt, r.rating, COUNT(l.id)) FROM Review r LEFT JOIN r.likes l GROUP BY r.id order by r.postedAt DESC ")
    Page<Review> findAllReviews(Pageable pageable);
}
