package com.example.be_java_hisp_w25_g01.repository.impl;

import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.exception.NotFoundException;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
public class UserRepositoryImpl implements IUserRepository {

    private IPostRepository postRepository;

    public UserRepositoryImpl(IPostRepository postRepository){
        this.postRepository = postRepository;
    }

    private List<User> userList = List.of(
            new User(1,"martinMarquez", List.of(5), null,postRepository.findByUser(1)),
            new User(2,"ariJaime", List.of(4,5), null, postRepository.findByUser(2)),
            new User(3,"ezeEscobar", List.of(4,5), null, postRepository.findByUser(3)),
            new User(4,"sofiaMaria", List.of(5), List.of(2,3),postRepository.findByUser(4)), // es vendedor
            new User(5,"leanSaracco", null, List.of(1,2,3,4),postRepository.findByUser(5))// es vendedor
    );




    public List<User> findAll() { return userList; }

    public Optional<User> findById(Integer id){

        return userList.stream()
                .filter(u -> Objects.equals(u.getUserId(), id))
                .findFirst();
    }

    @Override
    public void followUser(Integer userId, Integer userIdToFollow) {
        Optional<User> user = findById(userId);
        Optional<User> userToFollow = findById(userIdToFollow);
    }

}
