package controller;

import com.example.be_java_hisp_w25_g01.controller.ProductController;
import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.dto.response.PostsListDTO;
import com.example.be_java_hisp_w25_g01.exception.BadRequestException;
import com.example.be_java_hisp_w25_g01.service.IPostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import util.TestUtilGenerator;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    IPostService postService;

    @InjectMocks
    ProductController productController;

    @Test
    void getFollowedSellersDateOrderOk(){
        //Arrange
        String order = "date_asc";
        PostsListDTO expectedPosts = TestUtilGenerator.getPostListDTO();
        HttpStatus expectedStatus = HttpStatus.OK;

        when(postService.getLastPostsFollowedBy(expectedPosts.getUser_id(), order)).thenReturn(expectedPosts);

        //Act
        ResponseEntity<?> result = productController.listPosts(expectedPosts.getUser_id(), order);

        //Assert
        Assertions.assertEquals(expectedStatus, result.getStatusCode());
    }

//    @Test
//    void getFollowedSellersDateOrderBadRequest(){
//        //Arrange
//        String order = "invalid";
//        MessagesDTO expectedMessage = new MessagesDTO("Mal orden.");
//        HttpStatus expectedStatus = HttpStatus.BAD_REQUEST;
//
//        when(postService.getLastPostsFollowedBy(anyInt(), order)).thenReturn();
//
//        //Act
//        ResponseEntity<?> result = productController.listPosts(expectedPosts.getUser_id(), order);
//
//        //Assert
//        Assertions.assertEquals(expectedStatus, result.getStatusCode());
//    }


}
