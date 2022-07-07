package com.triple.travelerclubmileage.tripler.domain.review.listener;

import com.triple.travelerclubmileage.tripler.domain.review.entity.Review;
import com.triple.travelerclubmileage.tripler.domain.event.request.EventRequest;
import com.triple.travelerclubmileage.tripler.domain.user.entity.User;

public interface MileageEventProcessor {

    void onAdd(Review review, EventRequest request, User user);

    void onMod(Review review, EventRequest request, User user);

    void onDelete(Review review, EventRequest request, User user);
}
