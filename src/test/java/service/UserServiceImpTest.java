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
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.IntegerSyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    void followUserOkTest() {
        //Arrange
        Integer userId = 1;
        Integer userIdToFollow = 2;
        User expectedUser = new User(2,"ariJaime", null, null, null);

        when(userRepository.findById(userIdToFollow)).thenReturn(Optional.of(expectedUser));

        //Act
        MessagesDTO result = new MessagesDTO("User with id: 1 is now following user with id: 2");

        //Assert
        Assertions.assertEquals(expectedUser, result.getMessage());
    }
    @Test
    void followUserBadRequestTest() {

    }
}
