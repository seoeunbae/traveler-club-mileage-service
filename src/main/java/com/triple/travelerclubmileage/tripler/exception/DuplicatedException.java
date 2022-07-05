package com.triple.travelerclubmileage.tripler.exception;

public class DuplicatedException extends RuntimeException {

    public DuplicatedException(String message){
        super(message);
    }

    public static class EventDuplicatedException extends DuplicatedException{
        public EventDuplicatedException() {
            super("동일한 이벤트가 이미 반영되었습니다.");
        }
    }
}
