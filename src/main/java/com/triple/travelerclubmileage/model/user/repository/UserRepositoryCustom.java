package com.triple.travelerclubmileage.model.user.repository;

import java.sql.Timestamp;
import java.util.UUID;

public interface UserRepositoryCustom {
    Integer sumMileageByUserId(UUID userId);
}
