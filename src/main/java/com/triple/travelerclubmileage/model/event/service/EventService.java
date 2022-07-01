package com.triple.travelerclubmileage.model.event.service;

import com.triple.travelerclubmileage.model.event.entity.Event;
import com.triple.travelerclubmileage.model.event.repository.EventRepository;
import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.event.response.EventResponse;

import com.triple.travelerclubmileage.model.user.exception.UserException;
import com.triple.travelerclubmileage.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventResponse saveEvent(EventRequest request){
        log.info("리뷰 ID : "+request.getReviewId()+" , 행위 : "+request.getAction()+" , 유저 ID : "+request.getUserId());
        Event event = EventRequest.toEventEntity(request);
        event.setUser(
                userRepository.findByUserId(request.getUserId())
                        .orElseThrow(UserException.UserNotExistException::new)
        );
        return EventResponse.toResponse(eventRepository.save(event));
    }




}
