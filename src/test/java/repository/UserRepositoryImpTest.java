package repository;

import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.TestUtilGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepositoryImpTest {


    IUserRepository userRepository;

    @Test
    void findByIdOK(){
        Integer id = 1;

        User user = userRepository.findById(id).get();

        Assertions.assertEquals("martinMarquez", user.getUserName());
    }

    @Test
    void unfollowUserOk(){
        TestUtilGenerator util = new TestUtilGenerator();
        //Arrange
        User user1 = util.getUser();
        User user2 = new User(5,"leanSaracco", new ArrayList<>(List.of()), new ArrayList<>(List.of(1,2,3,4)),new ArrayList<>(List.of(4,5)));


        //act

        //assert
    }
}
