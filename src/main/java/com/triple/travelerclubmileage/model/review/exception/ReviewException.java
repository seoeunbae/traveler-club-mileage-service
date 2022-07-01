package com.triple.travelerclubmileage.model.review.exception;

public class ReviewException {
    public static class ReviewNotExistException extends RuntimeException{
        public ReviewNotExistException() {
            super("해당 리뷰가 존재하지 않습니다.");
        }
    }
}
