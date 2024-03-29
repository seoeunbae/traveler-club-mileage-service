package com.triple.travelerclubmileage.tripler.domain.review.repository;

import com.triple.travelerclubmileage.tripler.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
    Optional<Review> findById(UUID id);
}
