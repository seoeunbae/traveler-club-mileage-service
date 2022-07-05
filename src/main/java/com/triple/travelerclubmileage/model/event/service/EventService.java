package com.triple.travelerclubmileage.model.event.service;

import com.triple.travelerclubmileage.model.event.entity.Event;
import com.triple.travelerclubmileage.model.event.exception.EventException;
import com.triple.travelerclubmileage.model.event.repository.EventRepository;
import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.event.response.EventResponse;

import com.triple.travelerclubmileage.model.user.entity.User;
import com.triple.travelerclubmileage.model.user.exception.UserException;
import com.triple.travelerclubmileage.model.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
        final User user = userRepository.findById(request.getUserId())
                .orElseThrow(UserException.UserNotExistException::new);

        checkDuplicatedEvent(request, user);

        log.info("review ID : "+request.getReviewId()+" , action : "+request.getAction()+" , user ID : "+request.getUserId());

        Event event = EventRequest.toEventEntity(request);
        event.setUser( user );
        eventRepository.save(event);
        
        return EventResponse.toResponse(event);
    }

    private void checkDuplicatedEvent(EventRequest request, User user){
        eventRepository.findByUserAndContentAndEventTargetId(user, request.getContent(), request.getReviewId())
                .ifPresent(duplicated -> {
                    throw new EventException.EventDuplicatedException();
                });
    }




}
