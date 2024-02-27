package controller;

import com.example.be_java_hisp_w25_g01.controller.ProductController;
import com.example.be_java_hisp_w25_g01.service.IPostService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    IPostService postService;

    @InjectMocks
    ProductController productController;
}
