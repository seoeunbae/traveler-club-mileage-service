package com.triple.travelerclubmileage.domain.common.exception.handler;

import com.triple.travelerclubmileage.domain.common.rest.response.RestFailResponse;
import com.triple.travelerclubmileage.domain.user.exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(UserException.UserNotExistException.class)
    public ResponseEntity<RestFailResponse> NotFoundExceptionHandler(UserException.UserNotExistException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RestFailResponse.newInstance(HttpStatus.NOT_FOUND, ex.getClass().getSimpleName(), ex.getMessage()));
    }

    @ExceptionHandler()
    public ResponseEntity<RestFailResponse> NotFoundExceptionHandler(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RestFailResponse.newInstance(HttpStatus.NOT_FOUND,ex.getClass().getSimpleName(), ex.getMessage()));
    }

}
