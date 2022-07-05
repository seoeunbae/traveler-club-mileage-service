package com.triple.travelerclubmileage.tripler.domain.review.response;

import com.triple.travelerclubmileage.tripler.domain.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewResponse {
    private String reviewId;
    private String userId;
    private String placeId;

    public static ReviewResponse toResponse(Review review){
        return ReviewResponse.builder()
                .reviewId(review.getId().toString())
                .userId(review.getUser().getId().toString())
                .placeId(review.getPlace().getId().toString())
                .build();
    }
}
