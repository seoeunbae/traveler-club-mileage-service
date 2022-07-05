package com.triple.travelerclubmileage.tripler.domain.review.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.triple.travelerclubmileage.common.querydsl.QueryDslSupport;
import com.triple.travelerclubmileage.domain.review.entity.QReview;
import com.triple.travelerclubmileage.tripler.domain.review.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.UUID;

public class ReviewRepositoryImpl extends QueryDslSupport implements ReviewRepositoryCustom {
    @Autowired
    public ReviewRepositoryImpl(EntityManager entityManager) {
        super(Review.class, entityManager);
    }
    @Override
    public boolean existsByPlaceId(final UUID placeId) {
        return !queryFactory.selectFrom(QReview.review)
                .where(
                       checkPlaceId(placeId)
                )
                .limit(1)
                .fetch()
                .isEmpty();
    }

    private BooleanExpression checkPlaceId(UUID placeId){
        return placeId != null ? QReview.review.place.id.eq(placeId) : null;
    }

}
