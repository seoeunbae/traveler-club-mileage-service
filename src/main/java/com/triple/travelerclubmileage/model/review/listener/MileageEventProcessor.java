package com.triple.travelerclubmileage.model.review.listener;

import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.review.entity.Review;
import com.triple.travelerclubmileage.model.user.entity.User;

public interface MileageEventProcessor {
    void onAdd(Review review, EventRequest request, User user);
    void onMod(Review review, EventRequest request, User user);
    void onDelete(Review review, EventRequest request, User user);
}
