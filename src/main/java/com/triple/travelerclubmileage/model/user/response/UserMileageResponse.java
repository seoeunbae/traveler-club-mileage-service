package com.triple.travelerclubmileage.model.user.response;

import com.triple.travelerclubmileage.model.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
public class UserMileageResponse {
    private UUID userId;
    private Integer mileage;
//    public static UserMileageResponse toResponse(Integer mileage){
//        return UserMileageResponse.builder()
//                .userId()
//                .mileage(user.getMileage())
//                .build();
//    }


}
