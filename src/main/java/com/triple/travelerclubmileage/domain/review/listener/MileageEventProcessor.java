package com.triple.travelerclubmileage.domain.review.listener;

import com.triple.travelerclubmileage.domain.review.entity.Review;
import com.triple.travelerclubmileage.domain.event.request.EventRequest;
import com.triple.travelerclubmileage.domain.user.entity.User;

public interface MileageEventProcessor {
    void onAdd(Review review, EventRequest request, User user);
    void onMod(Review review, EventRequest request, User user);
    void onDelete(Review review, EventRequest request, User user);
}
