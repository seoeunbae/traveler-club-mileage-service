package com.triple.travelerclubmileage.tripler.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message){
        super(message);
    }

    public static class PlaceNotExistException extends NotFoundException{
        public PlaceNotExistException() {
            super("해당 장소가 존재하지 않습니다.");
        }
    }

    public static class ReviewNotExistException extends NotFoundException{
        public ReviewNotExistException() {
            super("해당 리뷰가 존재하지 않습니다.");
        }
    }

    public static class UserNotExistException extends NotFoundException {
        public UserNotExistException() {
            super("해당 유저가 존재하지 않습니다.");
        }
    }

    public static class PhotoNotExistException extends NotFoundException {
        public PhotoNotExistException() {
            super("해당 사진이 존재하지 않습니다.");
        }
    }

}
