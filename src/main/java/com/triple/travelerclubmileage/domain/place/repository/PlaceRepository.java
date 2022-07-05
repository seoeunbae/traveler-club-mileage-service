package com.triple.travelerclubmileage.domain.place.repository;

import com.triple.travelerclubmileage.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findById(UUID id);

}

