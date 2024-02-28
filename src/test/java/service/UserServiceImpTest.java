package service;

import com.example.be_java_hisp_w25_g01.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g01.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.exception.BadRequestException;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import util.TestUtilGenerator;
import util.TestUtilGenerator.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImpTest {

    @Mock
    IUserRepository userRepository;

    @Mock
    IPostRepository postRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void getFollowersCountOK(){
        User user = new User(1, "user_1", List.of(), List.of(2), List.of());
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));

        FollowersCountDTO result = userService.getFollowersCount(anyInt());

        Assertions.assertEquals(1, result.getFollowers_count());

    }


    @Test
    void getFollowersList_NotOk(){
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getUser()));
        when(userRepository.findAllByIdIn(List.of())).thenReturn(List.of(TestUtilGenerator.getUser()));

        Assertions.assertThrows(BadRequestException.class, () -> {
            //Code under test
            userService.getFollowersList(1,"mal");
        });
    }
}
