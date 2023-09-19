package com.delazeri.music.repositories;

import com.delazeri.music.domain.Like;
import com.delazeri.music.domain.Review;
import com.delazeri.music.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface LikeRepository extends JpaRepository<Like, UUID> {
    @Query("SELECT l FROM Like as l WHERE l.user = :user AND l.review =:review")
    Like findByUserAndReview(@Param("user") User user, @Param("review") Review review);
}
