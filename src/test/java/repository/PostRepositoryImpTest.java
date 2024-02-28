package repository;

import com.example.be_java_hisp_w25_g01.entity.Post;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.impl.PostRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostRepositoryImpTest {

    IPostRepository postRepository = new PostRepositoryImpl();

    @Test
    void findAllByIdIn(){
        List<Integer> postIds = List.of(2);

        List<Post> users = postRepository.findAllPostById(postIds);

        assertEquals(62, users.get(0).getProduct());

    }
}
