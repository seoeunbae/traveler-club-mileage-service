package com.triple.travelerclubmileage.model.user.service;

import com.triple.travelerclubmileage.model.common.rest.response.RestSuccessResponse;
import com.triple.travelerclubmileage.model.user.exception.UserException;
import com.triple.travelerclubmileage.model.user.repository.UserRepository;
import com.triple.travelerclubmileage.model.user.response.UserMileageResponse;
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
        Integer totalMileage = userRepository.findMileageByUserId(userId).orElseThrow(UserException.UserNotExistException::new);
        UserMileageResponse response = new UserMileageResponse();
        response.setUserId(userId);
        response.setMileage(totalMileage);
        return RestSuccessResponse.newInstance(response);

    }
}
