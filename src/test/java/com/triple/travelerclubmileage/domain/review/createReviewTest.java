package com.triple.travelerclubmileage.domain.review;

import com.triple.travelerclubmileage.mock.ReviewServiceBase;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

import javax.transaction.Transactional;

@Transactional
public class createReviewTest extends ReviewServiceBase {

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
        reviewRepository.deleteAll();
        photoRepository.deleteAll();
    }

    @Test
    @DisplayName("첫 리뷰0 / 사진0 / 콘텐트0 - 성공")
    public void createFirstReviewWithPhotoAndContentSuccess(){

    }

//    @Test
//    @DisplayName("첫 리뷰0 / 사진X / 콘텐트0 - 성공")
//    public void createFirstReviewWithContentSuccess(){
//
//    }
//
//    @Test
//    @DisplayName("첫 리뷰0 / 사진X / 콘텐트X - 성공")
//    public void createFirstReviewSuccess(){
//
//    }
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
