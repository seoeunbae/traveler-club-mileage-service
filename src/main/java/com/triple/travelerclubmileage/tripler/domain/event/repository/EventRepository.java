package com.triple.travelerclubmileage.tripler.domain.event.repository;

import com.triple.travelerclubmileage.tripler.domain.event.entity.Event;
import com.triple.travelerclubmileage.tripler.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByUserAndContentAndEventTargetId(User user, String content, UUID targetId);
}
