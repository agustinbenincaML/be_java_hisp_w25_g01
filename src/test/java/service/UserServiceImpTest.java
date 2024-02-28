package service;

import com.example.be_java_hisp_w25_g01.dto.response.*;

import com.example.be_java_hisp_w25_g01.entity.Post;
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
import util.TestUtilGenerator;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import util.TestUtilGenerator;
import util.TestUtilGenerator.*;

import javax.print.attribute.IntegerSyntax;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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

        User user = new User(userId, "user_1", List.of(), List.of(), List.of());
        User userToFollow = new User(userIdToFollow, "user_2", List.of(), List.of(), List.of());
        Post post = new Post();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.findById(userIdToFollow)).thenReturn(Optional.of(userToFollow));

        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(postRepository.findByUser(userIdToFollow)).thenReturn(posts);

        //Act
        MessagesDTO result = userService.followUser(userId, userIdToFollow);

        //Assert
        Assertions.assertEquals("User with id: 1 is now following user with id: 2", result.getMessage());
    }
    @Test
    void followUserBadRequestTest() {
        //Arrange
        Integer userId = 1;
        Integer userIdToFollow = 2;

        User user = new User(userId, "user_1", List.of(), List.of(), List.of());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.findById(userIdToFollow)).thenReturn(Optional.empty());

        //Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> userService.followUser(userId, userIdToFollow));
    }


    @Test
    void unfollowUserOk() {
        TestUtilGenerator utilGenerator = new TestUtilGenerator();

        //arrange
        User user1 = utilGenerator.getUser();
        User user2 = new User(5,"leanSaracco", new ArrayList<>(List.of()), new ArrayList<>(List.of(1,2,3,4)),new ArrayList<>(List.of(4,5)));

        MessagesDTO messageSpected = new MessagesDTO("User with id: " + user1.getUserId() + " is now unfollowing user with id: " + user2.getUserId());

        when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user1));
        when(userRepository.findById(user2.getUserId())).thenReturn(Optional.of(user2));


        //act
         MessagesDTO result = userService.unfollowUser(user1.getUserId(),user2.getUserId());
        //assertions
        Assertions.assertEquals(result, messageSpected);
    }
    @Test
    void unfollowUserBadRequest(){
        Integer userId = 1;
        Integer userIdToFollow = 2;

        User user = new User(userId, "user_1", List.of(), List.of(), List.of());

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.findById(userIdToFollow)).thenReturn(Optional.empty());

        //Act & Assert
        Assertions.assertThrows(BadRequestException.class, () -> userService.unfollowUser(userId, userIdToFollow));
    }

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

    @Test
    void getFollowedList_NotOk(){
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getUser()));
        when(userRepository.findAllByIdIn(List.of())).thenReturn(List.of(TestUtilGenerator.getUser()));

        Assertions.assertThrows(BadRequestException.class, () -> {
            //Code under test
            userService.getFollowersList(1,"mal");
        });
    }

    @Test
    void getFollowersList_Ok(){
        //arrange
        UserDTO user = TestUtilGenerator.getUserDTO();
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getUser()));
        when(userRepository.findAllByIdIn(List.of())).thenReturn(List.of(TestUtilGenerator.getUser()));

        //act
        FollowersDTO result = userService.getFollowersList(1,"name_asc");

        //assertion
        Assertions.assertEquals(user.getUser_id(), result.getUser_id());
        Assertions.assertEquals(user.getUser_name(), result.getUser_name());
    }

    @Test
    void getFollowedList_Ok(){
        //arrange
        UserDTO user = TestUtilGenerator.getUserDTO();
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getUser()));
        when(userRepository.findAllByIdIn(List.of(5))).thenReturn(List.of(TestUtilGenerator.getUser()));

        //act
        FollowedDTO result = userService.getFollowedList(1,"name_asc");

        //assertion
        Assertions.assertEquals(user.getUser_id(), result.getUser_id());
        Assertions.assertEquals(user.getUser_name(), result.getUser_name());
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