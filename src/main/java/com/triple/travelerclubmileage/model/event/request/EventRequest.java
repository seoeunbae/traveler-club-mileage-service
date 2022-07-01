package com.triple.travelerclubmileage.model.event.request;

import com.triple.travelerclubmileage.model.event.entity.Event;
import com.triple.travelerclubmileage.model.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
public class EventRequest {
    private Event.EventTargetType type;
    private Event.EventActionType action;
    private UUID reviewId;
    private String content;
    private UUID[] attachedPhotoIds;
    private UUID userId;
    private UUID placeId;

    public static Event toEventEntity(EventRequest request){
        Event event = new Event();
        event.setEventId(UUID.randomUUID());
        event.setEventTargetId(request.getReviewId());
        event.setEventActionType(request.getAction());
        event.setIsEnabled(true);
        event.setEventTargetType(request.getType());
        return event;
    }

    public static Review toReviewEntity(EventRequest request){
        Review review = new Review();
        review.setReviewId(request.getReviewId());
        review.setContent(request.getContent());
        review.setIsEnabled(true);
        return review;
    }
}
