package service;

import com.example.be_java_hisp_w25_g01.dto.request.PostDTO;
import com.example.be_java_hisp_w25_g01.dto.response.PostsListDTO;
import com.example.be_java_hisp_w25_g01.entity.Post;
import com.example.be_java_hisp_w25_g01.entity.Product;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.exception.BadRequestException;
import com.example.be_java_hisp_w25_g01.exception.NotFoundException;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IProductRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import util.TestUtilGenerator;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
    void getLastPostsFollowedBy_orderNull_Ok(){
        //Arrange
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getUser()));


        User user = TestUtilGenerator.getUser();
        user.setPosts(List.of(1, 2, 3));
        when(userRepository.findAllByIdIn(any())).thenReturn(List.of(user));

        List<Post> expected = List.of(new Post(1, 2, LocalDate.now().minusDays(1), 1, 1, 0.0),
                new Post(3, 2, LocalDate.now().minusDays(3), 1, 1, 0.0),
                new Post(2, 2, LocalDate.now().minusDays(2), 1, 1, 0.0));
        when(postRepository.findAllPostById(user.getPosts())).thenReturn(expected);

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(
                new Product(1,"Silla Gamer", "Gamer", "Razer", "Red & Black", "Special Edition")
        ));
        //Act
        PostsListDTO postsListDTO = postService.getLastPostsFollowedBy(1,  null);

        //Assert
        List<Integer> expectedIntegers = List.of(1, 3, 2);
        List<Integer> actualIntegers = postsListDTO.getPostsList().stream().map(PostDTO::getPost_id).toList();
        assertEquals(expectedIntegers, actualIntegers);
    }
    @Test
    void getLastPostsFollowedBy_orderDateAsc_Ok(){
        //Arrange
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getUser()));


        User user = TestUtilGenerator.getUser();
        user.setPosts(List.of(1, 2, 3));
        when(userRepository.findAllByIdIn(any())).thenReturn(List.of(user));

        List<Post> expected = List.of(new Post(1, 2, LocalDate.now().minusDays(1), 1, 1, 0.0),
                new Post(3, 2, LocalDate.now().minusDays(3), 1, 1, 0.0),
                new Post(2, 2, LocalDate.now().minusDays(2), 1, 1, 0.0));
        when(postRepository.findAllPostById(user.getPosts())).thenReturn(expected);

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(
                new Product(1,"Silla Gamer", "Gamer", "Razer", "Red & Black", "Special Edition")
        ));
        //Act
        PostsListDTO postsListDTO = postService.getLastPostsFollowedBy(1,  "date_asc");

        //Assert
        List<Integer> expectedIntegers = List.of(3, 2, 1);
        List<Integer> actualIntegers = postsListDTO.getPostsList().stream().map(PostDTO::getPost_id).toList();
        assertEquals(expectedIntegers, actualIntegers);
    }

    @Test
    void getLastPostsFollowedBy_orderDateDesc_Ok(){
        //Arrange
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getUser()));


        User user = TestUtilGenerator.getUser();
        user.setPosts(List.of(1, 2, 3));
        when(userRepository.findAllByIdIn(any())).thenReturn(List.of(user));

        List<Post> expected = List.of(new Post(1, 2, LocalDate.now().minusDays(1), 1, 1, 0.0),
                new Post(3, 2, LocalDate.now().minusDays(3), 1, 1, 0.0),
                new Post(2, 2, LocalDate.now().minusDays(2), 1, 1, 0.0));
        when(postRepository.findAllPostById(user.getPosts())).thenReturn(expected);

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(
                new Product(1,"Silla Gamer", "Gamer", "Razer", "Red & Black", "Special Edition")
        ));
        //Act
        PostsListDTO postsListDTO = postService.getLastPostsFollowedBy(1,  "date_desc");

        //Assert
        List<Integer> expectedIntegers = List.of(1,2,3);
        List<Integer> actualIntegers = postsListDTO.getPostsList().stream().map(PostDTO::getPost_id).toList();
        assertEquals(expectedIntegers, actualIntegers);
    }

    @Test
    void getLastPostsFollowedBy_userNotFound(){
        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> postService.getLastPostsFollowedBy(1, "date_asc"));
    }

    @Test
    void getLastPostsFollowedBy_orderDate_BadRequest(){
        //Arrange
        when(userRepository.findById(anyInt())).thenReturn(Optional.of(TestUtilGenerator.getUser()));


        User user = TestUtilGenerator.getUser();
        user.setPosts(List.of(1, 2, 3));
        when(userRepository.findAllByIdIn(any())).thenReturn(List.of(user));

        List<Post> expected = List.of(new Post(1, 2, LocalDate.now().minusDays(1), 1, 1, 0.0),
                new Post(3, 2, LocalDate.now().minusDays(3), 1, 1, 0.0),
                new Post(2, 2, LocalDate.now().minusDays(2), 1, 1, 0.0));
        when(postRepository.findAllPostById(user.getPosts())).thenReturn(expected);

        when(productRepository.findById(anyInt())).thenReturn(Optional.of(
                new Product(1,"Silla Gamer", "Gamer", "Razer", "Red & Black", "Special Edition")
        ));

        //Act & Assert
        assertThrows(BadRequestException.class, () -> postService.getLastPostsFollowedBy(1,  "mal"));

    }

}
