package com.triple.travelerclubmileage.model.user.repository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryCustom {
    Optional<Integer> findMileageByUserId(UUID userId);
}
