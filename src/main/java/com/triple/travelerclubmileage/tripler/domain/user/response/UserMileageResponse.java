package com.triple.travelerclubmileage.tripler.domain.user.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserMileageResponse {
    private UUID userId;
    private Integer mileage;
}
