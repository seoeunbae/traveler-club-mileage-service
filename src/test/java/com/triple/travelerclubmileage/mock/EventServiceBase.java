package com.triple.travelerclubmileage.mock;

import com.triple.travelerclubmileage.model.event.entity.Event;
import com.triple.travelerclubmileage.model.event.repository.EventRepository;
import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.event.service.EventService;
import com.triple.travelerclubmileage.model.user.entity.User;
import com.triple.travelerclubmileage.model.user.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class EventServiceBase {
    @Mock
    protected EventRepository eventRepository;
    @Mock
    protected UserRepository userRepository;
    @InjectMocks
    protected EventService eventService;
    protected final UUID eventId = UUID.randomUUID();
    protected final UUID userId = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");
    protected final UUID reviewId = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");
    protected final Event.EventActionType action = Event.EventActionType.ADD;
    protected final Event.EventTargetType type = Event.EventTargetType.REVIEW;
    final UUID placeId = UUID.fromString("2e4baf1c-5acb-4efb-a1af-eddada31b00f");
    final Integer initialMileage = 0;
    final UUID[] attachedPhotoIds = {UUID.fromString("e4d1a64e-a531-46de-88d0-ff0ed70c0bb9"), UUID.fromString("afb0cef2-851d-4a50-bb07-9cc15cbdc331")};
    public Event createMockEvent(){
        Event event = new Event();
        event.setId(eventId);
        event.setUser(createMockUser());
        event.setEventTargetId(reviewId);
        event.setEventTargetType(type);
        event.setEventActionType(action);
        event.setIsEnabled(true);
        return event;
    }

    public User createMockUser(){
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

    public EventRequest createMockEventRequest(){
        EventRequest request = new EventRequest();
        request.setUserId(userId);
        request.setContent("test_event_content");
        request.setAction(action);
        request.setPlaceId(placeId);
        request.setType(type);
        request.setReviewId(reviewId);
        request.setAttachedPhotoIds(attachedPhotoIds);
        return request;
    }
}
