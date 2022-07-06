package com.triple.travelerclubmileage.domain.review;

import com.triple.travelerclubmileage.mock.ReviewServiceBase;
import com.triple.travelerclubmileage.tripler.common.rest.response.RestSuccessResponse;
import com.triple.travelerclubmileage.tripler.domain.review.entity.Review;
import com.triple.travelerclubmileage.tripler.domain.review.response.ReviewResponse;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
public class createReviewTest extends ReviewServiceBase {

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
        placeRepository.deleteAll();
        reviewRepository.deleteAll();
        photoRepository.deleteAll();
    }
    @Before
    public void openMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("첫 리뷰0 / 사진0 / 콘텐트0 - 성공")
    public void createFirstReviewWithPhotoAndContentSuccess(){
        Mockito.doReturn(Optional.ofNullable(createUser())).when(userRepository).findById(Mockito.any(UUID.class));
        Mockito.doReturn(Optional.ofNullable(createPlace())).when(placeRepository).findById(Mockito.any(UUID.class));
        Mockito.doReturn(createFirstReview()).when(reviewRepository).save(Mockito.any(Review.class));
        Mockito.doReturn(true).when(reviewRepository).existsByPlaceId(Mockito.any(UUID.class));
        Mockito.doReturn(Optional.ofNullable(createPhoto1())).when(photoRepository).findById(photoId1);
        Mockito.doReturn(Optional.ofNullable(createPhoto2())).when(photoRepository).findById(photoId2);

        final RestSuccessResponse<ReviewResponse> result = reviewService.createReview(createEventRequest());
//        listener.changeMileage(createFirstReview(), createEventRequest(), createUser());

        Assertions.assertThat(result.getCode()).isEqualTo(200);
        Assertions.assertThat(result.getResult().getUserId()).isEqualTo(userId);
        Assertions.assertThat(result.getResult().getReviewId()).isEqualTo(reviewId);
        Assertions.assertThat(result.getResult().getPlaceId()).isEqualTo(placeId);
        Assertions.assertThat(result.getResult().getMileage()).isEqualTo(3);

    }

    @Test
    @DisplayName("첫 리뷰0 / 사진X / 콘텐트0 - 성공")
    public void createFirstReviewWithContentSuccess(){
        Mockito.doReturn(Optional.ofNullable(createUser())).when(userRepository).findById(Mockito.any(UUID.class));
        Mockito.doReturn(Optional.ofNullable(createPlace())).when(placeRepository).findById(Mockito.any(UUID.class));
        Mockito.doReturn(createFirstReview()).when(reviewRepository).save(Mockito.any(Review.class));
        Mockito.doReturn(Optional.ofNullable(createPhoto1())).when(photoRepository).findById(photoId1);
        Mockito.doReturn(Optional.ofNullable(createPhoto2())).when(photoRepository).findById(photoId2);
        Mockito.doReturn(true).when(reviewRepository).existsByPlaceId(placeId);

        final RestSuccessResponse<ReviewResponse> result = reviewService.createReview(createEventRequest());

        Assertions.assertThat(result.getCode()).isEqualTo(200);
        Assertions.assertThat(result.getResult().getUserId()).isEqualTo(userId);
        Assertions.assertThat(result.getResult().getReviewId()).isEqualTo(reviewId);
        Assertions.assertThat(result.getResult().getPlaceId()).isEqualTo(placeId);
        Assertions.assertThat(result.getResult().getMileage()).isEqualTo(2);

    }
//
    @Test
    @DisplayName("첫 리뷰0 / 사진X / 콘텐트X - 성공")
    public void createFirstReviewSuccess(){
        Mockito.doReturn(Optional.ofNullable(createUser())).when(userRepository).findById(Mockito.any(UUID.class));
        Mockito.doReturn(Optional.ofNullable(createPlace())).when(placeRepository).findById(Mockito.any(UUID.class));
        Mockito.doReturn(createFirstReview()).when(reviewRepository).save(Mockito.any(Review.class));
        Mockito.doReturn(Optional.ofNullable(createPhoto1())).when(photoRepository).findById(photoId1);
        Mockito.doReturn(Optional.ofNullable(createPhoto2())).when(photoRepository).findById(photoId2);
        Mockito.doReturn(true).when(reviewRepository).existsByPlaceId(placeId);
        final RestSuccessResponse<ReviewResponse> result = reviewService.createReview(createEventRequest());

        Assertions.assertThat(result.getCode()).isEqualTo(200);
        Assertions.assertThat(result.getResult().getUserId()).isEqualTo(userId);
        Assertions.assertThat(result.getResult().getReviewId()).isEqualTo(reviewId);
        Assertions.assertThat(result.getResult().getPlaceId()).isEqualTo(placeId);
        Assertions.assertThat(result.getResult().getMileage()).isEqualTo(1);

    }
//
//    @Test
//    @DisplayName("첫 리뷰X / 사진0 / 콘텐트0 - 성공")
//    public void createNotFirstReviewWithPhotoAndContentSuccess(){
//
//    }
//    @Test
//    @DisplayName("첫 리뷰X / 사진0 / 콘텐트X - 성공")
//    public void createNotFirstReviewWithPhotoSuccess(){
//
//    }
//    @Test
//    @DisplayName("첫 리뷰X / 사진X / 콘텐트0 - 성공")
//    public void createNotFirstReviewWithContentSuccess(){
//
//    }
//
//    @Test
//    @DisplayName("첫 리뷰X / 사진X / 콘텐트X - 성공")
//    public void createNotFirstReviewSuccess(){
//
//    }
}
