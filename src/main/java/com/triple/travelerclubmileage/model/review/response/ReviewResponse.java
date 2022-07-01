package com.triple.travelerclubmileage.model.review.response;

import com.triple.travelerclubmileage.model.review.entity.Review;
import lombok.Builder;

import java.util.UUID;
@Builder
public class ReviewResponse {
    private String reviewId;
    private String userId;
    private String placeId;

    public static ReviewResponse toResponse(Review review){
        return ReviewResponse.builder()
                .reviewId(review.getReviewId())
                .userId(review.getUser().getUserId())
                .placeId(review.getPlace().getPlaceId())
                .build();
    }
}
