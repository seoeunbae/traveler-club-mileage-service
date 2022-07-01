package com.triple.travelerclubmileage.model.review.service;

import com.triple.travelerclubmileage.model.event.entity.Event;
import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.photo.entity.Photo;
import com.triple.travelerclubmileage.model.photo.repository.PhotoRepository;
import com.triple.travelerclubmileage.model.place.exception.PlaceException;
import com.triple.travelerclubmileage.model.place.repository.PlaceRepository;
import com.triple.travelerclubmileage.model.review.entity.Review;
import com.triple.travelerclubmileage.model.review.exception.ReviewException;
import com.triple.travelerclubmileage.model.review.repository.ReviewRepository;
import com.triple.travelerclubmileage.model.review.response.ReviewResponse;
import com.triple.travelerclubmileage.model.user.entity.User;
import com.triple.travelerclubmileage.model.user.exception.UserException;
import com.triple.travelerclubmileage.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final PhotoRepository photoRepository;
    private Boolean isFirst(final UUID placeId){
        return !reviewRepository.existsByPlaceId(placeId);
    }
    private Boolean existsByContent(final String content){
        return content.length() >= 1;
    }
    private Boolean existsByPhoto(final UUID[] photos){
        return photos.length >= 1;
    }
    private void changeMileageByFirst(Review review, Event.EventActionType action,User user, UUID placeId){
        if (isFirst(placeId) && action.equals(Event.EventActionType.ADD)) {
            review.setIsFirst(true);
            user.setMileage(+1);
        } else if(review.getIsFirst() && action.equals(Event.EventActionType.DELETE)) user.setMileage(-1);
    }
    private void changeMileageByContent(String content,Event.EventActionType action, User user){
        if (existsByContent(content) && action.equals(Event.EventActionType.ADD) ) user.setMileage(+1);
        else if (existsByContent(content) && action.equals(Event.EventActionType.MOD) && content.length() < 1) user.setMileage(+1);//수정시, 기존에 리뷰의 글이 없다가 생긴경우, 마일리지 +1;
        else if (action.equals(Event.EventActionType.DELETE) && content.length() >= 1) user.setMileage(-1); //삭제시, 기존에 리뷰의 글이 있었던 경우, 마일리지 -1;
    }
    private void changeMileageByPhoto(User user, Event.EventActionType action, UUID[] photos ,UUID reviewId){
        List<Photo> allByReviewId = photoRepository.findAllByReviewId(reviewId);
        if(existsByPhoto(photos) && action.equals(Event.EventActionType.ADD)) user.setMileage(+1);
        else if (existsByPhoto(photos) && action.equals(Event.EventActionType.MOD) && allByReviewId.isEmpty()) { //수정시, 기존에 없던 이미지가 추가되는 경우, 마일리지 +1;
            user.setMileage(+1);
        } else if (action.equals(Event.EventActionType.DELETE) && allByReviewId.size() >= 1){//삭제시, 기존에 리뷰의 이미지가 1개이상인 경우, 마일리지 -1;
            user.setMileage(-1);
        }
    }

    public ReviewResponse createReview(
            final EventRequest request
    ){
        final User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(UserException.UserNotExistException::new);

        Review review = EventRequest.toReviewEntity(request);
        review.setUser(user);
        review.setPlace(
                placeRepository.findByPlaceId(request.getPlaceId())
                        .orElseThrow(PlaceException.PlaceNotExistException::new)
        );

        changeMileageByFirst(review, request.getAction(), user, request.getPlaceId());
        changeMileageByContent(request.getContent(),request.getAction(), user);
        changeMileageByPhoto(user, request.getAction(), request.getAttachedPhotoIds(),request.getReviewId());

        return ReviewResponse.toResponse(reviewRepository.save(review));

    }


    public ReviewResponse updateReview(
            final EventRequest request
    ){
        final User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(UserException.UserNotExistException::new);
        changeMileageByContent(request.getContent(), request.getAction(), user);
        changeMileageByPhoto(user, request.getAction(), request.getAttachedPhotoIds(), request.getReviewId());

        final Review review = reviewRepository.findByReviewId(request.getReviewId())
                .orElseThrow(ReviewException.ReviewNotExistException::new);

        List<Photo> photos = new ArrayList<>();
        for(UUID photoId :request.getAttachedPhotoIds()){
            photos.add(photoRepository.findByPhotoId(photoId).orElseThrow());
        }

        review.setContent(request.getContent());
        review.setPhotos(photos);
        return ReviewResponse.toResponse(review);
    }

    public ReviewResponse deleteReview(
            final EventRequest request
    ){
        final User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow(UserException.UserNotExistException::new);
        Review review = reviewRepository.findByReviewId(request.getReviewId()).orElseThrow(ReviewException.ReviewNotExistException::new);

        changeMileageByPhoto(user, request.getAction(), request.getAttachedPhotoIds(),request.getReviewId());
        changeMileageByContent(request.getContent(), request.getAction(), user);
        changeMileageByFirst(review, request.getAction(), user, request.getPlaceId());
        review.setIsEnabled(false);
        return null;
    }
}
