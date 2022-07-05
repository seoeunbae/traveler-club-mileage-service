package com.triple.travelerclubmileage.model.review.service;

import com.triple.travelerclubmileage.model.common.rest.response.RestResponse;
import com.triple.travelerclubmileage.model.common.rest.response.RestSuccessResponse;
import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.photo.entity.Photo;
import com.triple.travelerclubmileage.model.photo.repository.PhotoRepository;
import com.triple.travelerclubmileage.model.place.entity.Place;
import com.triple.travelerclubmileage.model.place.exception.PlaceException;
import com.triple.travelerclubmileage.model.place.repository.PlaceRepository;
import com.triple.travelerclubmileage.model.review.entity.Review;
import com.triple.travelerclubmileage.model.review.exception.ReviewException;
import com.triple.travelerclubmileage.model.review.listener.MileageListener;
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

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;
    private final PhotoRepository photoRepository;
    private final MileageListener listener;

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
        Review savedReview = reviewRepository.save(review);

        listener.changeMileage(savedReview, request, user);

        Arrays.stream(request.getAttachedPhotoIds()).forEach(photoId -> {
            Photo photo = new Photo();
            photo.setId(photoId);
            photo.setReview(savedReview);
            photo.setIsEnabled(true);
            photo.setType(Photo.PhotoType.REVIEW);
            photoRepository.save(photo);
        }); //photo리스트에 있는 사진들을 저장.

        return RestSuccessResponse.newInstance(ReviewResponse.toResponse(savedReview));

    }


    public RestResponse<ReviewResponse> updateReview(
            final EventRequest request
    ){
        final List<Photo> existedPhotos = photoRepository.findAllByReviewId(request.getReviewId());
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserException.UserNotExistException::new);
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(ReviewException.ReviewNotExistException::new);

        listener.changeMileage(review, request, user);

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
        final List<Photo> existedPhotos = photoRepository.findAllByReviewId(request.getReviewId());
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserException.UserNotExistException::new);

        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(ReviewException.ReviewNotExistException::new);
        review.setIsEnabled(false);

        listener.changeMileage(review, request, user);
        return null;
    }
}
