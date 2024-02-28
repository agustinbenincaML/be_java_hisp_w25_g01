package service;

import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.dto.response.UserDTO;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import util.TestUtilGenerator;

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
    void unfollowUserOk(){
        //arrange
        TestUtilGenerator utilGenerator = new TestUtilGenerator();
        UserDTO user = utilGenerator.getUserDTO();
        UserDTO userIdToUnfollow = utilGenerator.getUserDTO();
        MessagesDTO messageSpected = new MessagesDTO("User with id: " + user.getUser_id() + " is now unfollowing user with id: " + userIdToUnfollow.getUser_id());
        when(userRepository.findById(user.getUser_id())).thenReturn(user);

        //act

        //assertions
    }
}
