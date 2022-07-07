package com.triple.travelerclubmileage.tripler.common.exception.handler;

import com.triple.travelerclubmileage.tripler.common.rest.response.RestFailResponse;
import com.triple.travelerclubmileage.tripler.exception.DuplicatedException;
import com.triple.travelerclubmileage.tripler.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<RestFailResponse> NotFoundExceptionHandler(NotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(RestFailResponse.newInstance(HttpStatus.NOT_FOUND, ex.getClass().getSimpleName(), ex.getMessage()));
    }

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<RestFailResponse> DuplicatedExceptionHandler(DuplicatedException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(RestFailResponse.newInstance(HttpStatus.CONFLICT, ex.getClass().getSimpleName(), ex.getMessage()));
    }

}
