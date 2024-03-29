package com.triple.travelerclubmileage.tripler.domain.user.repository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryCustom {
    Optional<Integer> findMileageByUserId(UUID userId);
}
