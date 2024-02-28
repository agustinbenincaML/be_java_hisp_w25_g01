package repository;

import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class UserRepositoryImpTest {


    IUserRepository userRepository;

    @Test
    void findByIdOK(){
        Integer id = 1;

        User user = userRepository.findById(id).get();

        Assertions.assertEquals("martinMarquez", user.getUserName());
    }
}
