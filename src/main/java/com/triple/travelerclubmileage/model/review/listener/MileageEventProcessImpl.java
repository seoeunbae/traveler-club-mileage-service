package com.triple.travelerclubmileage.model.review.listener;

import com.triple.travelerclubmileage.model.event.request.EventRequest;
import com.triple.travelerclubmileage.model.photo.entity.Photo;
import com.triple.travelerclubmileage.model.photo.repository.PhotoRepository;
import com.triple.travelerclubmileage.model.review.entity.Review;
import com.triple.travelerclubmileage.model.review.repository.ReviewRepository;
import com.triple.travelerclubmileage.model.review.service.ReviewService;
import com.triple.travelerclubmileage.model.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Log4j2
@Transactional
@Component
@RequiredArgsConstructor
public class MileageEventProcessImpl implements MileageEventProcessor {
    private final ReviewRepository reviewRepository;
    private final PhotoRepository photoRepository;

    @Override
    public void onAdd(Review review, EventRequest request, User user) {
        System.out.println("ONADD");
        if( isFirst(request.getPlaceId())) {
            review.setIsFirst(true);
            log.info(user.getId()+" user.mileage+1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage() + 1);
        }
        if( existsByContent(request.getContent())) {
            log.info(user.getId()+" user.mileage+1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()+1);
        }
        if( existsByPhoto(request.getAttachedPhotoIds())){
            log.info(user.getId()+ " user.mileage+1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()+1);
        }
    }

    @Override
    public void onMod(Review review, EventRequest request, User user) {
        System.out.println("ONMOD");
        final List<Photo> existedPhotos = photoRepository.findAllByReviewId(request.getReviewId());

        if( existsByContent(request.getContent()) && request.getContent().length() < 1){
            log.info(user.getId()+" user.mileage+1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()+1);//수정시, 기존에 리뷰의 글이 없다가 생긴경우, 마일리지 +1;
        } else if( existsByContent(request.getContent()) && request.getContent().length() == 0){
            log.info(user.getId()+" user.mileage-1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()-1);//수정시, 기존에 리뷰의 글이 있다가 사라지는 경우, 마일리지 -1;
        }
        if( existsByPhoto(request.getAttachedPhotoIds()) && existedPhotos.isEmpty()){
            //수정시, 기존에 없던 이미지가 추가되는 경우, 마일리지 +1;
            log.info(user.getId()+ " user.mileage+1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()+1);
        }
    }

    @Override
    public void onDelete(Review review, EventRequest request, User user) {
        System.out.println("ONDELETE");
        final List<Photo> existedPhotos = photoRepository.findAllByReviewId(request.getReviewId());
        if (review.getIsFirst()) {
            log.info(user.getId() + " user.mileage-1 at " + LocalDateTime.now());
            user.setMileage(user.getMileage() - 1);
        }
        if (request.getContent().length() >= 1)   {
            //삭제시, 기존에 리뷰의 글이 있었던 경우, 마일리지 -1;
            log.info(user.getId()+" user.mileage-1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()-1);
        }
        if( existedPhotos.size() >= 1){
            //삭제시, 기존에 리뷰의 이미지가 1개이상인 경우, 마일리지 -1;
            log.info(user.getId()+ " user.mileage-1 at "+ LocalDateTime.now());
            user.setMileage(user.getMileage()-1);
        }
    }

    private boolean isFirst(final UUID placeId){
        return !reviewRepository.existsByPlaceId(placeId);
    }
    private Boolean existsByContent(final String content){
        return content.length() >= 1;
    }
    private Boolean existsByPhoto(final UUID[] photos){
        return photos.length >= 1;
    }
}
