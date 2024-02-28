package repository;

import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;


public class UserRepositoryImpTest {

    IUserRepository userRepository = new UserRepositoryImpl();

    @Test
    void findByIdOK(){
        Integer id = 1;

        User user = userRepository.findById(id).get();

        Assertions.assertEquals("martinMarquez", user.getUserName());
    }

    @Test
    void followUserOkTest(){
        Integer userId = 1;
        Integer userIdToFollow = 4;

        userRepository.followUser(userId, userIdToFollow);

        User user = userRepository.findById(userId).get(); //preguntar si esta ok llamar al metodo findById

        Assertions.assertTrue(user.getFollowed().contains(userIdToFollow));
    }

}
