package com.triple.travelerclubmileage.model.user.repository;

import com.triple.travelerclubmileage.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(UUID userId);
}
