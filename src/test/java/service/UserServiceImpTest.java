package service;

import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.TestUtilGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
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
    void unfollowUserOk() {
        TestUtilGenerator utilGenerator = new TestUtilGenerator();

        //arrange
        User user1 = utilGenerator.getUser();
        User user2 = new User(5,"leanSaracco", new ArrayList<>(List.of()), new ArrayList<>(List.of(1,2,3,4)),new ArrayList<>(List.of(4,5)));

        MessagesDTO messageSpected = new MessagesDTO("User with id: " + user1.getUserId() + " is now unfollowing user with id: " + user2.getUserId());

        when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user2));
        when(userRepository.findById(user2.getUserId())).thenReturn(Optional.of(user2));

        //act
         MessagesDTO result = userService.unfollowUser(user1.getUserId(),user2.getUserId());
        //assertions
        Assertions.assertEquals(result, messageSpected);
        verify(userRepository).unfollowUser(user1.getUserId(), user2.getUserId());

    }
}
