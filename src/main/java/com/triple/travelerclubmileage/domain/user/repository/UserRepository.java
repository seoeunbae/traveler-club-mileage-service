package com.triple.travelerclubmileage.domain.user.repository;

import com.triple.travelerclubmileage.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findById(UUID id);
}
