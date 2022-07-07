package com.triple.travelerclubmileage.tripler.domain.event.response;

import com.triple.travelerclubmileage.tripler.domain.event.entity.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
public class EventResponse {

    private UUID eventId;

    private Event.EventTargetType type;

    private Event.EventActionType action;

    private UUID TargetId;

    public static EventResponse toResponse(Event event){
        return EventResponse.builder()
                .TargetId(event.getEventTargetId())
                .eventId(event.getId())
                .type(event.getEventTargetType())
                .action(event.getEventActionType())
                .build();
    }
}
