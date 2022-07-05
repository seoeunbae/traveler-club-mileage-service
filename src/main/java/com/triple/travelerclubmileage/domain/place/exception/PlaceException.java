package com.triple.travelerclubmileage.domain.place.exception;

public class PlaceException {
    public static class PlaceNotExistException extends RuntimeException{
        public PlaceNotExistException() {
            super("해당 장소가 존재하지 않습니다.");
        }
    }
}
