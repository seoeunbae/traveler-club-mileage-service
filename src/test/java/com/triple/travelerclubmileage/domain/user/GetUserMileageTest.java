package com.triple.travelerclubmileage.domain.user;

import com.triple.travelerclubmileage.tripler.domain.common.rest.response.RestSuccessResponse;
import com.triple.travelerclubmileage.mock.UserServiceBase;
import com.triple.travelerclubmileage.tripler.domain.user.entity.User;
import com.triple.travelerclubmileage.tripler.domain.user.exception.UserException;
import com.triple.travelerclubmileage.tripler.domain.user.response.UserMileageResponse;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

@Transactional
public class GetUserMileageTest extends UserServiceBase {

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("유저의 마일리지 조회 - 성공")
    void getUserMileageSuccess(){
        User testUser = super.createUser();

        Mockito.doReturn(Optional.ofNullable(testUser.getMileage())).when(userRepository).findMileageByUserId(userId);

        final RestSuccessResponse<UserMileageResponse> result = userService.getUserMileage(userId);

        Assertions.assertThat(result.getCode()).isEqualTo(200);
        Assertions.assertThat(result.getResult().getMileage()).isEqualTo(0);
        Assertions.assertThat(result.getResult().getUserId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("유저의 마일리지 조회 - 실패 : 존재하지 않는 유저")
    void getUserMileageFailWithNoUser(){
        UserException.UserNotExistException thrown = Assert
                .assertThrows(UserException.UserNotExistException.class, () -> userService.getUserMileage(userId));

        assertEquals("해당 유저가 존재하지 않습니다.", thrown.getMessage());
    }

}