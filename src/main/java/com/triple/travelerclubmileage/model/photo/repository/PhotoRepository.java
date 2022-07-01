package com.triple.travelerclubmileage.model.photo.repository;

import com.triple.travelerclubmileage.model.photo.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByReviewId(UUID reviewId);
    Optional<Photo> findByPhotoId(UUID photoId);
}
