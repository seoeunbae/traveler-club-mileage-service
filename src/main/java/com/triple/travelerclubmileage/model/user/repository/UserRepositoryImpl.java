package com.triple.travelerclubmileage.model.user.repository;

import com.triple.travelerclubmileage.model.common.querydsl.QueryDslSupport;
import com.triple.travelerclubmileage.model.user.entity.QUser;
import com.triple.travelerclubmileage.model.user.entity.User;

import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.UUID;

public class UserRepositoryImpl extends QueryDslSupport implements UserRepositoryCustom {

    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class, entityManager);
    }


    @Override
    public Integer sumMileageByUserId(UUID userId) {
        return queryFactory.select(QUser.user.mileage)
                .from(QUser.user)
                .where(QUser.user.id.eq(userId))
                .fetchOne();
    }
}
