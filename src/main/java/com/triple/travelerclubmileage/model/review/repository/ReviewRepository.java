package com.triple.travelerclubmileage.model.review.repository;

import com.triple.travelerclubmileage.model.place.entity.Place;
import com.triple.travelerclubmileage.model.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
@Transactional
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>,ReviewRepositoryCustom {
    boolean existsByPlacePlaceId(String placeId);
    Optional<Review> findByReviewId(String reviewId);
}
