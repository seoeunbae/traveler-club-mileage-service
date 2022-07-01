package com.triple.travelerclubmileage.model.event.controller;

import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.event.response.EventResponse;
import com.triple.travelerclubmileage.model.event.service.EventService;
import com.triple.travelerclubmileage.model.review.response.ReviewResponse;
import com.triple.travelerclubmileage.model.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Transactional
@Log4j2
public class EventController {
    private final EventService eventService;
    private final ReviewService reviewService;

    @PostMapping
    public ReviewResponse runEvent(
            @RequestBody EventRequest request
    ){
        ReviewResponse response = null;
        eventService.saveEvent(request);
        switch (request.getAction()){
            case ADD:
                response = reviewService.createReview(request);
                break;
            case MOD:
                response = reviewService.updateReview(request);
                break;
            case DELETE:
                response = reviewService.deleteReview(request);
                break;
            default:
                log.warn("request action is incorrect!");
        }

        return response;
    }
}
