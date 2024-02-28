package controller;

import com.example.be_java_hisp_w25_g01.controller.UserController;

import com.example.be_java_hisp_w25_g01.dto.response.FollowedDTO;
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
import util.TestUtilGenerator;

import static org.mockito.ArgumentMatchers.anyInt;
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
        int userId = 1;
        int userIdToFollow = 4;
        MessagesDTO messageExpected = new MessagesDTO("User with id: 1 is now following user with id: 4");

        when(userService.followUser(userId, userIdToFollow)).thenReturn(messageExpected);

        //Act
        ResponseEntity<?> result = userController.followUser(userId, userIdToFollow);

        //Asert
        Assertions.assertEquals(messageExpected, result.getBody());
    }
    @Test
    void followUserUserNotFoundTest(){
        //Arrange
        MessagesDTO messageExpected = new MessagesDTO("User not found.");

        when(userService.followUser(anyInt(), anyInt())).thenReturn(messageExpected);

        //Act
        ResponseEntity<?> result = userController.followUser(anyInt(), anyInt());

        //Asert
        Assertions.assertEquals(messageExpected, result.getBody());
    }

    @Test
    void unfollowUserTestOk(){
        //Arrange
        MessagesDTO messageExpected = new MessagesDTO("User with id: 3 is now unfollowing user with id: 5");

        when(userService.unfollowUser(anyInt(),anyInt())).thenReturn(messageExpected);

        //act
        ResponseEntity<?> result = userController.unfollowUser(anyInt(),anyInt());
        //asserts
        Assertions.assertEquals(messageExpected, result.getBody());
    }

    //T-0004
    @Test
    void getFollowedSellersOK(){
        FollowedDTO followedDTO = new FollowedDTO(1,"martinMarquez", TestUtilGenerator.getUserDTOList());

        when(userService.getFollowedList(anyInt(), anyString())).thenReturn(followedDTO);

        ResponseEntity<FollowedDTO> result = userController.getFollowedSellers(anyInt(), anyString());

        Assertions.assertEquals(followedDTO, result.getBody());


    }
}
