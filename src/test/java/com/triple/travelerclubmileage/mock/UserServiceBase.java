//package com.triple.travelerclubmileage.mock;
//
//import com.triple.travelerclubmileage.model.user.entity.User;
//import com.triple.travelerclubmileage.model.user.repository.UserRepository;
//import com.triple.travelerclubmileage.model.user.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import javax.inject.Inject;
//import java.util.UUID;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceBase {
//    @Mock
//    private UserRepository userRepository;
//    @InjectMocks
//    private UserService userService;
//
//    final UUID userId = UUID.fromString("3ede0ef2-92b7-4817-a5f3-0c575361f745");
//    final Integer initialMileage = 0;
//
//    public User createMockNewUser(){
//        User user = new User();
//        user.setId(userId);
//        user.setMileage(initialMileage);
//        user.setIsEnabled(true);
//        user.setNickname("test_nickname");
//        user.setUsername("test_username");
//        user.setPhoneNumber("010-XXXX-XXXX");
//        user.setRole(User.UserRole.USER_ROLE);
//        return user;
//    }
//}
