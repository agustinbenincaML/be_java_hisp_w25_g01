package service;

import com.example.be_java_hisp_w25_g01.dto.request.PostDTO;
import com.example.be_java_hisp_w25_g01.dto.response.PostsListDTO;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.exception.BadRequestException;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IProductRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.TestUtilGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceImpTest {
    @Mock
    IPostRepository postRepository;
    @Mock
    IUserRepository userRepository;
    @Mock
    IProductRepository productRepository;

    @InjectMocks
    PostServiceImpl postService;

    @Test
    void getLastPostsFollowedByDateDescOkTest(){
        //Arrange
        String order = "date_desc";
        PostsListDTO expectedPosts = TestUtilGenerator.getPostListDTO();
        User user = TestUtilGenerator.getUser();

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.findAllByIdIn(user.getFollowed())).thenReturn(TestUtilGenerator.getUserList());
        when(postRepository.findAllPostById(List.of())).thenReturn(TestUtilGenerator.getPostList());
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getProduct()));

        //Act
        PostsListDTO currentPosts = postService.getLastPostsFollowedBy(user.getUserId(), order);

        //Assert
        Assertions.assertEquals(expectedPosts, currentPosts);
    }

    @Test
    void getLastPostsFollowedByDateAscOkTest(){
        //Arrange
        String order = "date_asc";
        PostsListDTO expectedPosts = TestUtilGenerator.getPostListDTO();
        List<PostDTO> reversedPosts = new ArrayList<>(expectedPosts.getPostsList());
        Collections.reverse(reversedPosts);
        expectedPosts = new PostsListDTO(expectedPosts.getUser_id(), reversedPosts);
        User user = TestUtilGenerator.getUser();

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.findAllByIdIn(user.getFollowed())).thenReturn(TestUtilGenerator.getUserList());
        when(postRepository.findAllPostById(List.of())).thenReturn(TestUtilGenerator.getPostList());
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getProduct()));

        //Act
        PostsListDTO currentPosts = postService.getLastPostsFollowedBy(user.getUserId(), order);

        //Assert
        Assertions.assertEquals(expectedPosts, currentPosts);
    }

    @Test
    void getLastPostsFollowedByBadRequestTest(){
        //Arrange
        String order = "order_invalid";
        String messageExpected = "Bad order request.";
        User user = TestUtilGenerator.getUser();

        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
        when(userRepository.findAllByIdIn(user.getFollowed())).thenReturn(TestUtilGenerator.getUserList());
        when(postRepository.findAllPostById(List.of())).thenReturn(TestUtilGenerator.getPostList());
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getProduct()));

        //Act
        BadRequestException currentThrown = Assertions.assertThrows(BadRequestException.class,() -> postService.getLastPostsFollowedBy(user.getUserId(), order));

        //Assert
        Assertions.assertEquals(messageExpected, currentThrown.getMessage());
    }
}
