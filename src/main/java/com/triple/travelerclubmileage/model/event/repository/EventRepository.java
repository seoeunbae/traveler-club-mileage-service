package com.triple.travelerclubmileage.model.event.repository;

import com.triple.travelerclubmileage.model.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
