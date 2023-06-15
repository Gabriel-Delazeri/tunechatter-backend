package com.delazeri.music.reviews.repositories;

import com.delazeri.music.reviews.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
}
