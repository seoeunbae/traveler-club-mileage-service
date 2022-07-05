package com.triple.travelerclubmileage.model.review.listener;

import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.review.entity.Review;
import com.triple.travelerclubmileage.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
@Log4j2
public class MileageListener {
    private final MileageEventProcessor processor;
    public void changeMileage(Review review, EventRequest request, User user){
        switch (request.getAction()) {
            case ADD:
                log.info("onAdd 실행");
                processor.onAdd(review, request, user);
                break;
            case MOD:
                log.info("onMod 실행");
                processor.onMod(review, request, user);
                break;
            case DELETE:
                log.info("onDelete 실행");
                processor.onDelete(review, request, user);
                break;
        }

    }
}
