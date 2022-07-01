package com.triple.travelerclubmileage.model.event.response;

import com.triple.travelerclubmileage.model.event.entity.Event;
import lombok.Builder;

import java.util.UUID;

@Builder
public class EventResponse {
    private String eventId;
    private Event.EventTargetType type;
    private Event.EventActionType action;
    private String TargetId;

    public static EventResponse toResponse(Event event){
        return EventResponse.builder()
                .TargetId(event.getEventTargetId())
                .eventId(event.getEventId())
                .type(event.getEventTargetType())
                .action(event.getEventActionType())
                .build();
    }
}
