package com.triple.travelerclubmileage.domain.user.repository;

import com.triple.travelerclubmileage.domain.common.querydsl.QueryDslSupport;
import com.triple.travelerclubmileage.domain.user.entity.QUser;
import com.triple.travelerclubmileage.domain.user.entity.User;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl extends QueryDslSupport implements UserRepositoryCustom {

    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class, entityManager);
    }


    @Override
    public Optional<Integer> findMileageByUserId(UUID userId) {
        return Optional.ofNullable(queryFactory.select(QUser.user.mileage)
                .from(QUser.user)
                .where(QUser.user.id.eq(userId))
                .fetchFirst());
    }
}
