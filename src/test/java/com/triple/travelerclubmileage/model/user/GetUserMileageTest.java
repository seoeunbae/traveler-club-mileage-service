package com.triple.travelerclubmileage.model.user;

import com.triple.travelerclubmileage.model.common.rest.response.RestSuccessResponse;
import com.triple.travelerclubmileage.model.user.entity.User;
import com.triple.travelerclubmileage.model.user.exception.UserException;
import com.triple.travelerclubmileage.model.user.repository.UserRepository;
import com.triple.travelerclubmileage.model.user.response.UserMileageResponse;
import com.triple.travelerclubmileage.model.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class GetUserMileageTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;
    final UUID userId = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");
    final Integer initialMileage = 0;
    public User createMockUser(){
        User user = new User();
        user.setId(userId);
        user.setMileage(initialMileage);
        user.setIsEnabled(true);
        user.setNickname("test_nickname");
        user.setUsername("test_username");
        user.setPhoneNumber("010-XXXX-XXXX");
        user.setRole(User.UserRole.USER_ROLE);
        return user;
    }

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("유저의 마일리지 조회 - 성공")
    void getUserMileage(){
        User testUser = createMockUser();

        Mockito.doReturn(Optional.ofNullable(testUser.getMileage())).when(userRepository).findMileageByUserId(userId);

        final RestSuccessResponse<UserMileageResponse> result = userService.getUserMileage(userId);

        Assertions.assertThat(result.getCode()).isEqualTo(200);
        Assertions.assertThat(result.getResult().getMileage()).isEqualTo(0);
        Assertions.assertThat(result.getResult().getUserId()).isEqualTo(userId);
    }

    @Test
    @DisplayName("유저의 마일리지 조회 - 실패 : 존재하지 않는 유저")
    void failToUserMileageFailWithNoUser(){
        UserException.UserNotExistException thrown = Assert.assertThrows(UserException.UserNotExistException.class, () -> userService.getUserMileage(userId));

        assertEquals("해당 유저가 존재하지 않습니다.", thrown.getMessage());
    }

}