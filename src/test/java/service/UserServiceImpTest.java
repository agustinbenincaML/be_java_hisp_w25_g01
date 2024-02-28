package service;

import com.example.be_java_hisp_w25_g01.dto.response.FollowedDTO;
import com.example.be_java_hisp_w25_g01.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g01.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g01.dto.response.UserDTO;
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

import java.util.Arrays;
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
    void getFollowedListAscOK(){
        User user = new User(2, "ariJaime", List.of(4,5), List.of(), List.of());
        List<User> followeds = List.of(new User(4,"sofiaMaria",List.of(),List.of(2),List.of()),
                new User(5,"leanSaracco",List.of(),List.of(2),List.of()));

        when(userRepository.findById(2)).thenReturn(Optional.of(user));
        when(userRepository.findAllByIdIn(List.of(4,5))).thenReturn(followeds);

        FollowedDTO result = userService.getFollowedList(2, "name_asc");

        Assertions.assertEquals(2,result.getUser_id());
        Assertions.assertEquals("ariJaime",result.getUser_name());
        Assertions.assertEquals(2,result.getFollowed().size());
        Assertions.assertEquals("leanSaracco",result.getFollowed().get(0).getUser_name());
        Assertions.assertEquals("sofiaMaria",result.getFollowed().get(1).getUser_name());

    }

    @Test
    void getFollowedListDescOK(){
        User user = new User(2, "ariJaime", List.of(4,5), List.of(), List.of());
        List<User> followeds = List.of(new User(4,"sofiaMaria",List.of(),List.of(2),List.of()),
                new User(5,"leanSaracco",List.of(),List.of(2),List.of()));

        when(userRepository.findById(2)).thenReturn(Optional.of(user));
        when(userRepository.findAllByIdIn(List.of(4,5))).thenReturn(followeds);

        FollowedDTO result = userService.getFollowedList(2, "name_desc");

        Assertions.assertEquals(2,result.getUser_id());
        Assertions.assertEquals("ariJaime",result.getUser_name());
        Assertions.assertEquals(2,result.getFollowed().size());
        Assertions.assertEquals("sofiaMaria",result.getFollowed().get(0).getUser_name());
        Assertions.assertEquals("leanSaracco",result.getFollowed().get(1).getUser_name());

    }

    @Test
    void getFollowersListAscOk() {
        User user = new User(4, "sofiaMaria", List.of(), List.of(2, 3), List.of());
        List<User> followers = List.of(new User(2, "ariJaime", List.of(4), List.of(), List.of()),
                new User(3, "ezeEscobar", List.of(4), List.of(), List.of()));
        when(userRepository.findById(4)).thenReturn(Optional.of(user));
        when(userRepository.findAllByIdIn(List.of(2, 3))).thenReturn(followers);


        FollowersDTO expectedResult = FollowersDTO.builder()
                .user_id(4)
                .user_name("sofiaMaria")
                .followers(Arrays.asList(
                        UserDTO.builder().user_id(2).user_name("ariJaime").build(),
                        UserDTO.builder().user_id(3).user_name("ezeEscobar").build()
                ))
                .build();

        FollowersDTO result = userService.getFollowersList(4, "name_asc");



        // Assert
        Assertions.assertEquals(expectedResult, result);

    }

    @Test
    void getFollowersListDescOk() {
        User user = new User(4, "sofiaMaria", List.of(), List.of(2, 3), List.of());
        List<User> followers = List.of(new User(2, "ariJaime", List.of(4), List.of(), List.of()),
                new User(3, "ezeEscobar", List.of(4), List.of(), List.of()));
        when(userRepository.findById(4)).thenReturn(Optional.of(user));
        when(userRepository.findAllByIdIn(List.of(2, 3))).thenReturn(followers);


        FollowersDTO expectedResult = FollowersDTO.builder()
                .user_id(4)
                .user_name("sofiaMaria")
                .followers(Arrays.asList(
                        UserDTO.builder().user_id(3).user_name("ezeEscobar").build(),
                        UserDTO.builder().user_id(2).user_name("ariJaime").build()
                ))
                .build();

        FollowersDTO result = userService.getFollowersList(4, "name_desc");



        // Assert
        Assertions.assertEquals(expectedResult, result);

    }

}
