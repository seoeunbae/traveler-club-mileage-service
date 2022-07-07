package com.triple.travelerclubmileage.tripler.domain.review.listener;

import com.triple.travelerclubmileage.tripler.domain.review.entity.Review;
import com.triple.travelerclubmileage.tripler.domain.event.request.EventRequest;
import com.triple.travelerclubmileage.tripler.domain.photo.entity.Photo;
import com.triple.travelerclubmileage.tripler.domain.photo.repository.PhotoRepository;
import com.triple.travelerclubmileage.tripler.domain.review.repository.ReviewRepository;
import com.triple.travelerclubmileage.tripler.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
        int mileage = 0;
        if( isFirst(request.getPlaceId())) {
            review.setIsFirst(true);
            log.info("["+request.getAction()+"]"+" user_id : "+ user.getId() + " , '해당 장소의 첫 리뷰' 로 마일리지 + 1  :: "+ LocalDateTime.now());
            mileage += 1;
        }
        if( existsByContent(request.getContent())) {
            log.info("["+request.getAction()+"]"+" user_id : "+ user.getId() + " , '1자 이상의 콘텐츠' 로 마일리지 + 1  :: "+ LocalDateTime.now());
            mileage += 1;
        }
        if( existsByPhoto(request.getAttachedPhotoIds())){
            log.info("["+request.getAction()+"]"+" user_id : "+ user.getId() + " , '1개 이상의 사진' 으로 마일리지 + 1  :: "+ LocalDateTime.now());
            mileage += 1;
        }
        commitChange(user, mileage);
    }

    @Override
    public void onMod(Review review, EventRequest request, User user) {
        final List<Photo> existedPhotos = photoRepository.findAllByReviewId(request.getReviewId());
        int mileage = 0;
        if( existsByContent(request.getContent()) && request.getContent().length() < 1){
            //수정시, 기존에 리뷰의 글이 없다가 생긴경우, 마일리지 +1;
            log.info("["+request.getAction()+"]"+" user_id : "+ user.getId() + " , '1자 이상의 콘텐츠' 로 마일리지 + 1  :: "+ LocalDateTime.now());
            mileage += 1;
        } else if( existsByContent(request.getContent()) && request.getContent().length() == 0){
            //수정시, 기존에 리뷰의 글이 있다가 사라지는 경우, 마일리지 -1;
            log.info("["+request.getAction()+"]"+" user_id : "+ user.getId() + " , '1자 이상의 콘텐츠' 로 얻은 마일리지 - 1  :: "+ LocalDateTime.now());
            mileage -= 1;
        }

        if( existsByPhoto(request.getAttachedPhotoIds()) && existedPhotos.isEmpty()){
            //수정시, 기존에 없던 이미지가 추가되는 경우, 마일리지 +1;
            log.info("["+request.getAction()+"]"+" user_id : "+ user.getId() + " , '1개 이상의 사진' 로 마일리지 + 1  :: "+ LocalDateTime.now());
            mileage += 1;
        } else if (!existsByPhoto(request.getAttachedPhotoIds()) && !existedPhotos.isEmpty()){
            //수정시, 기존에 있던 이미지가 없어지는 경우, 마일리지 -1;
            log.info("["+request.getAction()+"]"+" user_id : "+ user.getId() + " , '1개 이상의 사진' 으로 얻은 마일리지 - 1  :: "+ LocalDateTime.now());
            mileage -= 1;
        }
        commitChange(user, mileage);

    }

    @Override
    public void onDelete(Review review, EventRequest request, User user) {
        final List<Photo> existedPhotos = photoRepository.findAllByReviewId(request.getReviewId());
        int mileage = 0;
        if ( review.getIsFirst()) {
            log.info("["+request.getAction()+"]"+" user_id : "+ user.getId() + " , '해당 장소의 첫 리뷰' 로 얻은 마일리지 - 1  :: "+ LocalDateTime.now());
            mileage -= 1;
        }
        if ( review.getContent().length() >= 1)   {
            //삭제시, 기존에 리뷰의 글이 있었던 경우, 마일리지 -1;
            log.info("["+request.getAction()+"]"+" user_id : "+ user.getId() + " , '1자 이상의 콘텐츠' 로 얻은 마일리지 - 1  :: "+ LocalDateTime.now());
            mileage -= 1;
        }
        if( existedPhotos.size() >= 1){
            //삭제시, 기존에 리뷰의 이미지가 1개이상인 경우, 마일리지 -1;
            log.info("["+request.getAction()+"]"+" user_id : "+ user.getId() + " , '1개 이상의 사진' 으로 얻은 마일리지 -은1  :: "+ LocalDateTime.now());
            mileage -= 1;
        }
        commitChange(user, mileage);
    }

    private void commitChange(User user, Integer mileage){
        user.setMileage(user.getMileage() + mileage);
    }

    private boolean isFirst(final UUID placeId){
        return !reviewRepository.existsByPlaceId(placeId);
    }
    private boolean existsByContent(final String content){
        if( content == null){
            return false;
        }
        return content.length() >= 1;
    }
    private boolean existsByPhoto(final UUID[] photos){
        if( photos == null){
            return false;
        } else return photos.length >= 1;
    }
}
