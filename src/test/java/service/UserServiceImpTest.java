package service;

import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.dto.response.UserDTO;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper =new ModelMapper();

    @Test
    void unfollowUserOk(){
        TestUtilGenerator utilGenerator = new TestUtilGenerator();

        //arrange


        MessagesDTO messageSpected = new MessagesDTO("User with id: " + userDTO.getUser_id() + " is now unfollowing user with id: " + userIdToUnfollow.getUser_id());

        when(userRepository.findById(userDTO.getUser_id())).thenReturn(userExpected);

        //act

        //assertions
    }
}
