package com.triple.travelerclubmileage.tripler.domain.event.service;

import com.triple.travelerclubmileage.tripler.domain.event.entity.Event;
import com.triple.travelerclubmileage.tripler.domain.event.request.EventRequest;
import com.triple.travelerclubmileage.tripler.domain.event.response.EventResponse;
import com.triple.travelerclubmileage.tripler.domain.event.repository.EventRepository;

import com.triple.travelerclubmileage.tripler.domain.user.entity.User;
import com.triple.travelerclubmileage.tripler.domain.user.repository.UserRepository;
import com.triple.travelerclubmileage.tripler.exception.DuplicatedException;
import com.triple.travelerclubmileage.tripler.exception.NotFoundException;
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
        final User user = userRepository.findById(request.getUserId())
                .orElseThrow(NotFoundException.UserNotExistException::new);

        checkDuplicatedEvent(request, user);

        log.info("[saveEvent()] review ID : "+request.getReviewId()+" , action : "+request.getAction()+" , user ID : "+request.getUserId());

        Event event = EventRequest.toEventEntity(request);
        event.setUser( user );


        return EventResponse.toResponse(eventRepository.save(event));
    }

    private void checkDuplicatedEvent(EventRequest request, User user){
        eventRepository.findByUserAndContentAndEventTargetId(user, request.getContent(), request.getReviewId())
                .ifPresent(duplicated -> {
                    throw new DuplicatedException.EventDuplicatedException();
                });
    }




}
