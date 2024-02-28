package util;

import com.example.be_java_hisp_w25_g01.dto.request.PostDTO;
import com.example.be_java_hisp_w25_g01.dto.request.ProductDTO;
import com.example.be_java_hisp_w25_g01.dto.response.UserDTO;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TestUtilGenerator {

    public static UserDTO getUserDTO(){
        return new UserDTO(1,"martinMarquez");
    }
    public static User getUser() {return new User(1,"martinMarquez", new ArrayList<>(List.of(5)), new ArrayList<>(List.of()), new ArrayList<>(List.of()));}

    public static List<UserDTO> getUserDTOList(){
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
