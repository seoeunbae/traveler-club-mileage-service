package com.triple.travelerclubmileage.model.event.exception;

public class EventException {
    public static class EventDuplicatedException extends RuntimeException{
        public EventDuplicatedException() {
            super("동일한 이벤트가 이미 반영되었습니다.");
        }
    }
}
