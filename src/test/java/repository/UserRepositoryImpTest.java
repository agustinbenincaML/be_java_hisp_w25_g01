package repository;

import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryImpTest {

    IUserRepository userRepository = new UserRepositoryImpl();

    @Test
    void findByIdOK(){
        Integer id = 1;

        User user = userRepository.findById(id).get();

        assertEquals("martinMarquez", user.getUserName());
    }

    @Test
    void unfollowUserOk() {
        // Arrange
        User user1 = new User(1,"martinMarquez", new ArrayList<>(List.of(5)), new ArrayList<>(List.of()), new ArrayList<>(List.of()) );
        User userToUnfollow = new User(5,"leanSaracco", new ArrayList<>(List.of()), new ArrayList<>(List.of(1,2,3,4)),new ArrayList<>(List.of(4,5)));

        System.out.println("Antes de unfollowUser:");
        System.out.println("user1.getFollowed(): " + user1.getFollowed());
        System.out.println("userToUnfollow.getFollowers(): " + userToUnfollow.getFollowers());

        // Act
        userRepository.unfollowUser(1, 5);

        // Assert
        assertFalse(userRepository.findById(user1.getUserId()).get().getFollowed().contains(userToUnfollow.getUserId()));
        assertFalse(userRepository.findById(userToUnfollow.getUserId()).get().getFollowers().contains(user1.getUserId()));
    }

    @Test
    void followUserOkTest(){
        Integer userId = 1;
        Integer userIdToFollow = 4;

        userRepository.followUser(userId, userIdToFollow);

        User user = userRepository.findById(userId).get(); //preguntar si esta ok llamar al metodo findById

        assertTrue(user.getFollowed().contains(userIdToFollow));
    }



    @Test
    void findAllByIdIn() {
        List<Integer> useIds = List.of(1);

        List<User> users = userRepository.findAllByIdIn(useIds);

        assertEquals("martinMarquez", users.get(0).getUserName());

    }
    @Test
    void findByIdInOK(){
        //Arrange
        List<Integer> idList = List.of(1,2);

        //Act
        List<User> currentUsers = userRepository.findAllByIdIn(idList);

        //Assert
        Assertions.assertEquals("martinMarquez", currentUsers.get(0).getUserName());
        Assertions.assertEquals("ariJaime", currentUsers.get(1).getUserName());
    }

}
