package com.triple.travelerclubmileage.tripler.domain.review.repository;

import java.util.UUID;

public interface ReviewRepositoryCustom {
    boolean existsByPlaceId(UUID placeId);
}
