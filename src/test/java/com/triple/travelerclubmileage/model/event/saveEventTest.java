package com.triple.travelerclubmileage.model.event;

import com.triple.travelerclubmileage.mock.EventServiceBase;
import com.triple.travelerclubmileage.model.event.entity.Event;
import com.triple.travelerclubmileage.model.event.repository.EventRepository;
import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.event.response.EventResponse;
import com.triple.travelerclubmileage.model.event.service.EventService;
import com.triple.travelerclubmileage.model.user.entity.User;
import com.triple.travelerclubmileage.model.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class saveEventTest extends EventServiceBase {
    @AfterEach
    void tearDown(){
        eventRepository.deleteAll();
        userRepository.deleteAll();
    }
    @Before
    public void openMocks(){
        MockitoAnnotations.openMocks(this);

    }
    @Test
    @DisplayName("발생한 이벤트 저장 - 성공")
    public void saveEventSuccess(){
        User mockUser = createMockUser();
        Event mockEvent = createMockEvent();
        EventRequest mockEventRequest = createMockEventRequest();

        Mockito.doReturn(Optional.ofNullable(mockUser)).when(userRepository).findById(Mockito.any(UUID.class));

        final EventResponse result = eventService.saveEvent(mockEventRequest);

        Assertions.assertThat(result.getEventId()).isExactlyInstanceOf(UUID.class);
        Assertions.assertThat(result.getAction()).isEqualTo(action);
        Assertions.assertThat(result.getType()).isEqualTo(type);
        Assertions.assertThat(result.getTargetId()).isEqualTo(reviewId);

    }

//    @Test
//    @DisplayName("발생한 이벤트 저장 - 실패 : 존재하지 않는 유저")
//    void saveEventFailWithNoUser(){
//
//    }
//
//    @Test
//    @DisplayName("발생한 이벤트 저장 - 실패 : 지정되지 않은 행위")
//    void saveEventFailWithNoAction(){
//
//    }

//    @Test
//    @DisplayName("발생한 이벤트 저장 - 실패 : 존재하지 않는 리뷰")
//    public void saveEventFailWithNoReview(){
//
//    }
//
//    @Test
//    @DisplayName("발생한 이벤트 저장 - 실패 : 존재하지 않는 장소")
//    public void saveEventFailWithNoPlace(){
//
//
//    }

}
