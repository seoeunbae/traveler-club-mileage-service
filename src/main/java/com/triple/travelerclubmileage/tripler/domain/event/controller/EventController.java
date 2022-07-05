package com.triple.travelerclubmileage.tripler.domain.event.controller;

import com.triple.travelerclubmileage.common.rest.response.RestResponse;
import com.triple.travelerclubmileage.tripler.domain.event.request.EventRequest;
import com.triple.travelerclubmileage.tripler.domain.event.service.EventService;
import com.triple.travelerclubmileage.tripler.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Transactional
@Log4j2
public class EventController {
    private final EventService eventService;
    private final ReviewService reviewService;

    @PostMapping
    public RestResponse runEvent(
            @RequestBody EventRequest request
    ){
        eventService.saveEvent(request);
        switch (request.getAction()){
            case ADD:
                return reviewService.createReview(request);
            case MOD:
                return reviewService.updateReview(request);
            case DELETE:
                return reviewService.deleteReview(request);
            default:
                log.warn("request action is incorrect!");
                throw new RuntimeException("request action is incorrect!");
        }
    }
}
