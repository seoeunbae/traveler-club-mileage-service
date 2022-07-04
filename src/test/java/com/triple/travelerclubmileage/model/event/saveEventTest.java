package com.triple.travelerclubmileage.model.event;

import com.triple.travelerclubmileage.model.event.entity.Event;
import com.triple.travelerclubmileage.model.event.repository.EventRepository;
import com.triple.travelerclubmileage.model.event.service.EventService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class saveEventTest {
    @Mock
    private EventRepository eventRepository;
    @InjectMocks
    private EventService eventService;

//    final UUID eventId = UUID.randomUUID();
//    final UUID userId = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");
//    final UUID reviewId = UUID.fromString("240a0658-dc5f-4878-9381-ebb7b2667772");
//
//    public Event createMockEvent(){
//        Event event = new Event();
//        event.setUser();
//        event.setEventTargetId();
//        event.setEventActionType();
//        return event;
//    }
}
