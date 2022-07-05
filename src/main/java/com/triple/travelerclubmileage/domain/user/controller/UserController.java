package com.triple.travelerclubmileage.domain.user.controller;

import com.triple.travelerclubmileage.domain.common.rest.response.RestResponse;
import com.triple.travelerclubmileage.domain.user.response.UserMileageResponse;
import com.triple.travelerclubmileage.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController("/users")
@RequiredArgsConstructor
@Transactional
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}/mileage")
    public RestResponse<UserMileageResponse> getUserMileage(
          @PathVariable("userId") UUID userId
    ) {
        return userService.getUserMileage(userId);
    }
}
