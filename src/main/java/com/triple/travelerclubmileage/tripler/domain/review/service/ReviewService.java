package com.triple.travelerclubmileage.tripler.domain.review.service;

import com.triple.travelerclubmileage.tripler.domain.review.response.ReviewResponse;
import com.triple.travelerclubmileage.tripler.domain.common.rest.response.RestResponse;
import com.triple.travelerclubmileage.tripler.domain.common.rest.response.RestSuccessResponse;
import com.triple.travelerclubmileage.tripler.domain.event.request.EventRequest;
import com.triple.travelerclubmileage.tripler.domain.photo.entity.Photo;
import com.triple.travelerclubmileage.tripler.domain.photo.repository.PhotoRepository;
import com.triple.travelerclubmileage.tripler.domain.place.entity.Place;
import com.triple.travelerclubmileage.tripler.domain.place.repository.PlaceRepository;
import com.triple.travelerclubmileage.tripler.domain.review.entity.Review;
import com.triple.travelerclubmileage.tripler.domain.review.listener.MileageListener;
import com.triple.travelerclubmileage.tripler.domain.review.repository.ReviewRepository;
import com.triple.travelerclubmileage.tripler.domain.user.entity.User;
import com.triple.travelerclubmileage.tripler.domain.user.repository.UserRepository;
import com.triple.travelerclubmileage.tripler.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
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
                .orElseThrow(NotFoundException.UserNotExistException::new);
         final Place place = placeRepository.findById(request.getPlaceId())
                .orElseThrow(NotFoundException.PlaceNotExistException::new);

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
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(NotFoundException.UserNotExistException::new);
        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(NotFoundException.ReviewNotExistException::new);

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
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(NotFoundException.UserNotExistException::new);

        Review review = reviewRepository.findById(request.getReviewId())
                .orElseThrow(NotFoundException.ReviewNotExistException::new);
        review.setIsEnabled(false);

        listener.changeMileage(review, request, user);
        return null;
    }
}
