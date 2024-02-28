package controller;

import com.example.be_java_hisp_w25_g01.controller.ProductController;
import com.example.be_java_hisp_w25_g01.dto.response.PostsListDTO;
import com.example.be_java_hisp_w25_g01.service.IPostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    IPostService postService;

    @InjectMocks
    ProductController productController;

    @Test
    void listPostOrderByNull(){
        //Arrange
        PostsListDTO postsListDTOexpected = new PostsListDTO();
        when(postService.getLastPostsFollowedBy(anyInt(), eq(""))).thenReturn(postsListDTOexpected);

        //Act
        ResponseEntity<PostsListDTO> postsListDTOResponseEntity = productController.listPosts(1, "");

        //Assert
        assertEquals(postsListDTOexpected, postsListDTOResponseEntity.getBody());
    }
    @Test
    void listPostOrderAnyString(){
        //Arrange
        PostsListDTO postsListDTOexpected = new PostsListDTO();
        when(postService.getLastPostsFollowedBy(anyInt(), anyString())).thenReturn(postsListDTOexpected);

        //Act
        ResponseEntity<PostsListDTO> postsListDTOResponseEntity = productController.listPosts(anyInt(),anyString());

        //Assert
        assertEquals(postsListDTOexpected, postsListDTOResponseEntity.getBody());
    }
}
