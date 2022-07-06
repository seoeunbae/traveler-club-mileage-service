package com.triple.travelerclubmileage.tripler.domain.user.service;

import com.triple.travelerclubmileage.tripler.common.rest.response.RestSuccessResponse;
import com.triple.travelerclubmileage.tripler.domain.user.repository.UserRepository;
import com.triple.travelerclubmileage.tripler.domain.user.response.UserMileageResponse;
import com.triple.travelerclubmileage.tripler.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public RestSuccessResponse<UserMileageResponse> getUserMileage(UUID userId){
        Integer totalMileage = userRepository.findMileageByUserId(userId).orElseThrow(NotFoundException.UserNotExistException::new);
        UserMileageResponse response = new UserMileageResponse();
        response.setUserId(userId);
        response.setMileage(totalMileage);
        return RestSuccessResponse.newInstance(response);

    }
}
