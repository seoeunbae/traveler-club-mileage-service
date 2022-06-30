package com.triple.travelerclubmileage.model.event.request;

import com.triple.travelerclubmileage.model.event.entity.Event;
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
}
