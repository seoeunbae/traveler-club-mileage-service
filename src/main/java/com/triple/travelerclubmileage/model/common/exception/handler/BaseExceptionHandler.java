package com.triple.travelerclubmileage.model.common.exception.handler;

import com.triple.travelerclubmileage.model.common.rest.response.RestFailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RestFailResponse> NotFoundExceptionHandler(RuntimeException ex){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(RestFailResponse.newInstance(HttpStatus.NOT_FOUND,ex.getClass().getSimpleName(), ex.getMessage()));
    }

}
