package com.triple.travelerclubmileage.tripler.common.rest.response;

import lombok.Getter;

@Getter
public abstract class RestResponse<T> {
    int code;
}
