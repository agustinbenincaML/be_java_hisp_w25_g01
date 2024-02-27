package controller;

import com.example.be_java_hisp_w25_g01.controller.UserController;
import com.example.be_java_hisp_w25_g01.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g01.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.service.IUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    IUserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void followUserOkTest(){
        //Arrange
        Integer userId = 1;
        Integer userIdToFollow = 4;
        MessagesDTO messegaExpected = new MessagesDTO("User with id: 1 is now following user with id: 4");

        when(userService.followUser(userId, userIdToFollow)).thenReturn(messegaExpected);

        //Act
        ResponseEntity<?> result = userController.followUser(userId, userIdToFollow);

        //Asert
        Assertions.assertEquals(messegaExpected, result.getBody());
    }

    @Test
    void followUserUserNotFoundTest(){
        //Arrange
        MessagesDTO messegaExpected = new MessagesDTO("User not found.");

        when(userService.followUser(anyInt(), anyInt())).thenReturn(messegaExpected);

        //Act
        ResponseEntity<?> result = userController.followUser(anyInt(), anyInt());

        //Asert
        Assertions.assertEquals(messegaExpected, result.getBody());
    }

    //T-0007
    @Test
    void getFollowersCountOK(){
        FollowersCountDTO followersCountDTO = new FollowersCountDTO(1, "user_1", 10L);

        when(userService.getFollowersCount(anyInt())).thenReturn(followersCountDTO);

        ResponseEntity<FollowersCountDTO> result = (ResponseEntity<FollowersCountDTO>) userController.getFollowersCount(anyInt());

        Assertions.assertEquals(followersCountDTO.getFollowers_count(), result.getBody().getFollowers_count());

    }
}
