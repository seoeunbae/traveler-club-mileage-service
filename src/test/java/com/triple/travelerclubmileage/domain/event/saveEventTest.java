package com.triple.travelerclubmileage.domain.event;

import com.triple.travelerclubmileage.mock.EventServiceBase;
import com.triple.travelerclubmileage.tripler.domain.event.entity.Event;
import com.triple.travelerclubmileage.tripler.domain.event.request.EventRequest;
import com.triple.travelerclubmileage.tripler.domain.event.response.EventResponse;
import com.triple.travelerclubmileage.tripler.domain.user.entity.User;
import com.triple.travelerclubmileage.tripler.exception.DuplicatedException;
import com.triple.travelerclubmileage.tripler.exception.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

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
        Mockito.doReturn(Optional.ofNullable(createUser())).when(userRepository).findById(Mockito.any(UUID.class));
        Mockito.doReturn(createEvent()).when(eventRepository).save(Mockito.any(Event.class));

        final EventResponse result = eventService.saveEvent(createEventRequest());

        Assertions.assertThat(result.getEventId()).isExactlyInstanceOf(UUID.class);
        Assertions.assertThat(result.getAction()).isEqualTo(action);
        Assertions.assertThat(result.getType()).isEqualTo(type);
        Assertions.assertThat(result.getTargetId()).isEqualTo(reviewId);

    }

    @Test
    @DisplayName("발생한 이벤트 저장 - 실패 : 존재하지 않는 유저")
    public void saveEventFailWithNoUser(){
        NotFoundException.UserNotExistException thrown = Assert
                .assertThrows(NotFoundException.UserNotExistException.class, () -> eventService.saveEvent(createEventRequest()));

        assertEquals("해당 유저가 존재하지 않습니다.", thrown.getMessage());
    }

    @Test
    @DisplayName("발생한 이벤트 저장 - 실패 : 중복된 이벤트")
    public void saveEventFailWithNoAction(){
        User mockUser = createUser();
        Event mockEvent = createEvent();
        EventRequest mockEventRequest1 = createEventRequest();

        Mockito.doReturn(Optional.ofNullable(mockUser)).when(userRepository).findById(Mockito.any(UUID.class));
        Mockito.doReturn(Optional.ofNullable(mockEvent)).when(eventRepository).findByUserAndContentAndEventTargetId(mockUser, mockEventRequest1.getContent(), mockEventRequest1.getReviewId());

        DuplicatedException.EventDuplicatedException thrown = Assert
                .assertThrows(DuplicatedException.EventDuplicatedException.class, () -> eventService.saveEvent(mockEventRequest1));

        assertEquals("동일한 이벤트가 이미 반영되었습니다.", thrown.getMessage());
    }

}
