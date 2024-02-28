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
import java.util.OptionalInt;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class UserRepositoryImpTest {


    IUserRepository userRepository = new UserRepositoryImpl();

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

        when(userRepository.findById(user1.getUserId())).thenReturn(Optional.of(user1));
        when(userRepository.findById(user2.getUserId())).thenReturn(Optional.of(user2));

        //act
        userRepository.unfollowUser(user1.getUserId(), user2.getUserId());

        //assert
        verify(userRepository).findById(user1.getUserId());
        verify(userRepository).findById(user2.getUserId());

        verify(user1.getFollowed()).remove(user2.getUserId());
        verify(user2.getFollowers()).remove(user1.getUserId());
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
