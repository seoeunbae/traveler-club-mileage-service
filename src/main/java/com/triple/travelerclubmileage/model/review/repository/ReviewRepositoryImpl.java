package com.triple.travelerclubmileage.model.review.repository;

import com.triple.travelerclubmileage.model.common.querydsl.QueryDslSupport;
import com.triple.travelerclubmileage.model.review.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class ReviewRepositoryImpl extends QueryDslSupport implements ReviewRepositoryCustom {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewRepositoryImpl(ReviewRepository reviewRepository, EntityManager entityManager) {
        super(Review.class, entityManager);
        this.reviewRepository = reviewRepository;
    }

    @Override
    public boolean existsByPlaceId(String placeId) {
        return false;
    }

}
