package com.triple.travelerclubmileage.model.review.repository;

import com.triple.travelerclubmileage.model.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Boolean existsByPlaceId(UUID placeId);
    Optional<Review> findByReviewId(UUID reviewId);
}
