package com.triple.travelerclubmileage.tripler.common.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;

public abstract class QueryDslSupport extends QuerydslRepositorySupport {
    protected JPAQueryFactory queryFactory;
    public QueryDslSupport(Class<?> classType, EntityManager entityManager) {
        super(classType);
        super.setEntityManager(entityManager);

        this.queryFactory = new JPAQueryFactory(entityManager);
    }
}
