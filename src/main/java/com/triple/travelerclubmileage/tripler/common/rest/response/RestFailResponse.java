package com.triple.travelerclubmileage.tripler.common.rest.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestFailResponse<T> extends RestResponse<T> {
    String reason;
    String type;

    public RestFailResponse(HttpStatus httpStatus, String reason) {
        this.code = httpStatus.value();
        this.reason = reason;
    }

    public RestFailResponse(HttpStatus httpStatus, String reason, String type) {
        this.code = httpStatus.value();
        this.type = type;
        this.reason = reason;
    }

    public static <T> RestFailResponse<T> newInstance(HttpStatus httpStatus, String type, String reason) {
        return new RestFailResponse<T>(httpStatus, reason, type);
    }

}
