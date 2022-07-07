package com.triple.travelerclubmileage.tripler.domain.review.response;

import com.triple.travelerclubmileage.tripler.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class ReviewResponse {

    private UUID reviewId;

    private UUID userId;

    private UUID placeId;

    private int mileage;

    public static ReviewResponse toResponse(Review review){
        return ReviewResponse.builder()
                .reviewId(review.getId())
                .userId(review.getUser().getId())
                .placeId(review.getPlace().getId())
                .mileage(review.getUser().getMileage())
                .build();
    }
}
