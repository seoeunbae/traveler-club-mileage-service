package com.triple.travelerclubmileage.mock;

import com.triple.travelerclubmileage.tripler.domain.event.entity.Event;
import com.triple.travelerclubmileage.tripler.domain.event.request.EventRequest;
import com.triple.travelerclubmileage.tripler.domain.photo.entity.Photo;
import com.triple.travelerclubmileage.tripler.domain.photo.repository.PhotoRepository;
import com.triple.travelerclubmileage.tripler.domain.place.entity.Place;
import com.triple.travelerclubmileage.tripler.domain.place.repository.PlaceRepository;
import com.triple.travelerclubmileage.tripler.domain.review.entity.Review;
import com.triple.travelerclubmileage.tripler.domain.review.listener.MileageListener;
import com.triple.travelerclubmileage.tripler.domain.review.repository.ReviewRepository;
import com.triple.travelerclubmileage.tripler.domain.review.service.ReviewService;
import com.triple.travelerclubmileage.tripler.domain.user.entity.User;
import com.triple.travelerclubmileage.tripler.domain.user.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceBase {
    @Mock
    protected ReviewRepository reviewRepository;
    @Mock
    protected UserRepository userRepository;
    @Mock
    protected PlaceRepository placeRepository;
    @Mock
    protected PhotoRepository photoRepository;
    @Mock
    protected MileageListener listener;
    @InjectMocks
    protected ReviewService reviewService;
    protected final UUID eventId = UUID.randomUUID();
    protected final UUID userId = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");
    protected final UUID reviewId = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");
    protected final Event.EventActionType action = Event.EventActionType.ADD;
    protected final Event.EventTargetType eventType = Event.EventTargetType.REVIEW;
    protected final Place.PlaceType placeType = Place.PlaceType.FOOD;
    final UUID placeId = UUID.fromString("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
    final Integer initialMileage = 0;
    final UUID[] attachedPhotoIds = {UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb9"), UUID.fromString("afb0cef2-851d-4a50-bb07-9cc15cbdc331")};

    public User createUser(){
        User user = new User();
        user.setId(userId);
        user.setMileage(initialMileage);
        user.setIsEnabled(true);
        user.setNickname("test_nickname");
        user.setUsername("test_username");
        user.setPhoneNumber("010-XXXX-XXXX");
        user.setRole(User.UserRole.USER_ROLE);
        return user;
    }
    public Event createEvent(){
        Event event = new Event();
        event.setId(eventId);
        event.setUser(createUser());
        event.setEventTargetId(reviewId);
        event.setEventTargetType(eventType);
        event.setEventActionType(action);
        event.setContent("test_event_content");
        event.setIsEnabled(true);
        return event;
    }

    public EventRequest createEventRequest(){
        EventRequest request = new EventRequest();
        request.setUserId(userId);
        request.setContent("test_event_content");
        request.setAction(action);
        request.setPlaceId(placeId);
        request.setType(eventType);
        request.setReviewId(reviewId);
        request.setAttachedPhotoIds(attachedPhotoIds);
        return request;
    }

    public Review createFirstReview(){
        Review review = new Review();
        review.setIsFirst(true);
        review.setContent("test_event_content");
        review.setId(reviewId);
        review.setUser(createUser());
        review.setIsEnabled(true);
        review.setPlace(createPlace());
        review.setPhotos(createPhotos());
        return review;
    }

    public Review createNotFirstReview(){
        Review review = new Review();
        review.setIsFirst(false);
        review.setContent("test_event_content");
        review.setIsEnabled(true);
        review.setId(reviewId);
        review.setUser(createUser());
        review.setPlace(createPlace());
        review.setPhotos(createPhotos());
        return review;
    }

    public Place createPlace(){
        Place place = new Place();
        place.setId(placeId);
        place.setType(placeType);
        place.setDescription("test_place_description");
        place.setLocation("test_place_location");
        place.setName("test_place_name");
        return place;
    }

    public List<Photo> createPhotos(){
        List<Photo> photos = new ArrayList<>();
        Arrays.stream(attachedPhotoIds).forEach(photoId -> {
            Photo photo = new Photo();
            photo.setId(photoId);
            photo.setIsEnabled(true);
            photo.setType(Photo.PhotoType.REVIEW);
            photo.setResource("test_photo_dataurl");
            photos.add(photo);
        });
        return photos;
    }
}
