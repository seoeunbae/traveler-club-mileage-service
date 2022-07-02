package com.triple.travelerclubmileage.model.review.repository;

import java.util.UUID;

public interface ReviewRepositoryCustom {
    boolean existsByPlaceId(UUID placeId);
}
