package com.triple.travelerclubmileage.tripler.domain.event.request;

import com.sun.istack.NotNull;
import com.triple.travelerclubmileage.tripler.domain.review.entity.Review;
import com.triple.travelerclubmileage.tripler.domain.event.entity.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EventRequest {
    @NotNull
    private Event.EventTargetType type;
    @NotNull
    private Event.EventActionType action;
    @NotNull
    private UUID reviewId;
    @NotNull
    private String content;
    private UUID[] attachedPhotoIds;
    @NotNull
    private UUID userId;
    @NotNull
    private UUID placeId;

    public static Event toEventEntity(EventRequest request){
        Event event = new Event();
        event.setEventTargetId(request.getReviewId());
        event.setEventActionType(request.getAction());
        event.setIsEnabled(true);
        event.setEventTargetType(request.getType());
        event.setContent(request.getContent());
        return event;
    }

    public static Review toReviewEntity(EventRequest request){
        Review review = new Review();
        review.setId(request.getReviewId());
        review.setContent(request.getContent());
        review.setIsEnabled(true);
        return review;
    }
}
