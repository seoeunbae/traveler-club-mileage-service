package com.triple.travelerclubmileage.model.review.service;

import com.triple.travelerclubmileage.model.common.rest.response.RestResponse;
import com.triple.travelerclubmileage.model.common.rest.response.RestSuccessResponse;
import com.triple.travelerclubmileage.model.event.entity.Event;
import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.photo.entity.Photo;
import com.triple.travelerclubmileage.model.photo.repository.PhotoRepository;
import com.triple.travelerclubmileage.model.place.entity.Place;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private static final Logger logger = LogManager.getLogger(ReviewService.class);
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final PhotoRepository photoRepository;
    public boolean isFirst(final UUID placeId){
        return !reviewRepository.existsByPlaceId(placeId);
    }
    public Boolean existsByContent(final String content){
        return content.length() >= 1;
    }
    public Boolean existsByPhoto(final UUID[] photos){
        return photos.length >= 1;
    }
    public void changeMileageByFirst(Review review, Event.EventActionType action, User user, UUID placeId){
        if (isFirst(placeId) && action.equals(Event.EventActionType.ADD)) {
            review.setIsFirst(true);
            logger.info(user.getId()+" user.mileage+1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()+1);
        } else if(action.equals(Event.EventActionType.DELETE)){
            if(review.getIsFirst() ){
                logger.info(user.getId()+ " user.mileage-1 at "+ LocalDateTime.now());
                user.setMileage(user.getMileage()-1);
            }
        }
    }
    public void changeMileageByContent(String content,Event.EventActionType action, User user){

        if (existsByContent(content) && action.equals(Event.EventActionType.ADD) )   {
            logger.info(user.getId()+" user.mileage+1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()+1);
        } else if (existsByContent(content) && action.equals(Event.EventActionType.MOD) && content.length() < 1) {
            logger.info(user.getId()+" user.mileage+1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()+1);//수정시, 기존에 리뷰의 글이 없다가 생긴경우, 마일리지 +1;
        } else if (!existsByContent(content) && action.equals(Event.EventActionType.MOD) && content.length()==0)  {
            logger.info(user.getId()+" user.mileage-1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()-1);//수정시, 기존에 리뷰의 글이 있다가 사라지는 경우, 마일리지 -1;
        } else if (action.equals(Event.EventActionType.DELETE) && content.length() >= 1)   {
            logger.info(user.getId()+" user.mileage-1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()-1); //삭제시, 기존에 리뷰의 글이 있었던 경우, 마일리지 -1;
        }
    }
    public void changeMileageByPhoto(User user, Event.EventActionType action, UUID[] photos ,UUID reviewId){
        List<Photo> allByReviewId = photoRepository.findAllByReviewId(reviewId);

        if(existsByPhoto(photos) && action.equals(Event.EventActionType.ADD))    {
            logger.info(user.getId()+ " user.mileage+1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()+1);
        } else if (existsByPhoto(photos) && action.equals(Event.EventActionType.MOD) && allByReviewId.isEmpty()) { //수정시, 기존에 없던 이미지가 추가되는 경우, 마일리지 +1;
            logger.info(user.getId()+ " user.mileage+1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()+1);
        } else if (action.equals(Event.EventActionType.DELETE) && allByReviewId.size() >= 1){//삭제시, 기존에 리뷰의 이미지가 1개이상인 경우, 마일리지 -1;
            logger.info(user.getId()+ " user.mileage-1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()-1);
        }
    }

    public RestResponse<ReviewResponse> createReview(
            final EventRequest request
    ){
         User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserException.UserNotExistException::new);
         final Place place = placeRepository.findById(request.getPlaceId())
                .orElseThrow(PlaceException.PlaceNotExistException::new);
        Review review = EventRequest.toReviewEntity(request);
        review.setUser(user);
        review.setPlace(place);

        changeMileageByFirst(review, request.getAction(), user, request.getPlaceId());
        changeMileageByContent(request.getContent(),request.getAction(), user);
        changeMileageByPhoto(user, request.getAction(), request.getAttachedPhotoIds(),request.getReviewId());
        Review savedReview = reviewRepository.save(review);

        Arrays.stream(request.getAttachedPhotoIds()).forEach(photoId -> {
            Photo photo = new Photo();
            photo.setId(photoId);
            photo.setReview(review);
            photo.setIsEnabled(true);
            photo.setType(Photo.PhotoType.REVIEW);
//            photo.setResource();
            photoRepository.save(photo);
        });//photo리스트에 있는 사진들을 저장.

        return RestSuccessResponse.newInstance(ReviewResponse.toResponse(savedReview));

    }


    public RestResponse<ReviewResponse> updateReview(
            final EventRequest request
    ){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserException.UserNotExistException::new);
        changeMileageByContent(request.getContent(), request.getAction(), user);
        changeMileageByPhoto(user, request.getAction(), request.getAttachedPhotoIds(), request.getReviewId());

        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(ReviewException.ReviewNotExistException::new);

        List<Photo> photos = new ArrayList<>();
        for(UUID photoId :request.getAttachedPhotoIds()){
            photos.add(photoRepository.findById(photoId).orElseThrow());
        }

        review.setContent(request.getContent());
        review.setPhotos(photos);
        return RestSuccessResponse.newInstance(ReviewResponse.toResponse(review));
    }

    public RestResponse<ReviewResponse> deleteReview(
            final EventRequest request
    ){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserException.UserNotExistException::new);

        Review review = reviewRepository.findById(request.getReviewId()).orElseThrow(ReviewException.ReviewNotExistException::new);

        changeMileageByPhoto(user, request.getAction(), request.getAttachedPhotoIds(),request.getReviewId());
        changeMileageByContent(request.getContent(), request.getAction(), user);
        changeMileageByFirst(review, request.getAction(), user, request.getPlaceId());
        review.setIsEnabled(false);
        return null;
    }
}
