package util;

import com.example.be_java_hisp_w25_g01.dto.request.PostDTO;
import com.example.be_java_hisp_w25_g01.dto.request.ProductDTO;
import com.example.be_java_hisp_w25_g01.dto.response.UserDTO;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestUtilGenerator {

    public UserDTO getUserDTO(){
        return new UserDTO(1,"martinMarquez");
    }

    public List<UserDTO> getUserDTOList(){
        UserDTO user1 = new UserDTO(2,"ariJaime");
        UserDTO user2 = new UserDTO(3,"ezeEscobar");
        return new ArrayList<>(List.of(user1, user2));
    }

    public PostDTO getPostDTO(){
        ProductDTO product = new ProductDTO();
        product.setProduct_id(1);
        product.setProduct_name("Silla Gamer");
        product.setType("Gamer");
        product.setBrand("Racer");

        return new PostDTO(1, LocalDate.of(2024,01,02), product, 100, 1500.50);
    }
}
