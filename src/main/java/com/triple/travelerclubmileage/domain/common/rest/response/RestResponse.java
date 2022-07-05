package com.triple.travelerclubmileage.domain.common.rest.response;

import lombok.Getter;

@Getter
public abstract class RestResponse<T> {
    int code;
}
