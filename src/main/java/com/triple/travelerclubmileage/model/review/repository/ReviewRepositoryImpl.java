package com.triple.travelerclubmileage.model.review.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.triple.travelerclubmileage.model.common.querydsl.QueryDslSupport;
import com.triple.travelerclubmileage.model.place.entity.QPlace;
import com.triple.travelerclubmileage.model.review.entity.QReview;
import com.triple.travelerclubmileage.model.review.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;
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
