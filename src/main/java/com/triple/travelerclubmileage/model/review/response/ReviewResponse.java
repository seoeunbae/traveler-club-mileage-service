package com.triple.travelerclubmileage.model.review.response;

import com.triple.travelerclubmileage.model.review.entity.Review;
import lombok.Builder;

import java.util.UUID;
@Builder
public class ReviewResponse {
    private UUID reviewId;
    private UUID userId;
    private UUID placeId;

    public static ReviewResponse toResponse(Review review){
        return ReviewResponse.builder()
                .reviewId(review.getReviewId())
                .userId(review.getUser().getUserId())
                .placeId(review.getPlace().getPlaceId())
                .build();
    }
}
