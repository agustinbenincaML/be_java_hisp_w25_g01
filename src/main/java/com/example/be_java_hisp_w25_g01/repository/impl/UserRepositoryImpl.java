package com.example.be_java_hisp_w25_g01.repository.impl;

import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.entity.Post;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.exception.NotFoundException;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class UserRepositoryImpl implements IUserRepository {

    IPostRepository postRepository;


    private List<User> userList;
    @Autowired
    public UserRepositoryImpl(IPostRepository postRepository){
        this.postRepository = postRepository;
        loadUserList();
    }

   

    private List<User> loadUserList(){
        userList = new ArrayList<>();
        userList.add(new User(1,"martinMarquez", new ArrayList<>(List.of(5)), new ArrayList<>(List.of()),postRepository.findByUser(1)));
        userList.add(new User(2,"ariJaime", new ArrayList<>(List.of(4,5)), new ArrayList<>(List.of()), postRepository.findByUser(2)));
        userList.add(new User(3,"ezeEscobar", new ArrayList<>(List.of(4,5)), new ArrayList<>(List.of()), postRepository.findByUser(3)));
        userList.add(new User(4,"sofiaMaria", new ArrayList<>(List.of(5)), new ArrayList<>(List.of(2,3)),postRepository.findByUser(4))); // es vendedor
        userList.add(new User(5,"leanSaracco", new ArrayList<>(List.of()), new ArrayList<>(List.of(1,2,3,4)),postRepository.findByUser(5)));// es vendedor
        return userList;
    }
    public void createPost(Post post){
        Optional<User> userOp = findById(post.getUser_id());
        if(userOp.isPresent()){
            User user = userOp.get();
            List<Post> userPosts = new ArrayList<>(user.getPosts());
            userPosts.add(post);
            user.setPosts(userPosts);
            postRepository.addPost(post);
        }
    }


    public List<User> findAll() { return userList; }

    public Optional<User> findById(Integer id){

        return userList.stream()
                .filter(u -> Objects.equals(u.getUserId(), id))
                .findFirst();
    }

    @Override
    public void followUser(Integer UserId, Integer userIdToFollow) {
        Optional<User> user = findById(UserId);
        Optional<User> userToFollow = findById(userIdToFollow);

        if(UserId == userIdToFollow) {throw new NotFoundException("User cannot follow itself.");}

        if(user.isEmpty() || userToFollow.isEmpty()) {throw new NotFoundException("User not found.");}

        if(postRepository.findByUser(userIdToFollow).isEmpty()) {throw new NotFoundException("The user you want to follow is not a seller");}

        if(user.get().getFollowed().contains(userIdToFollow)) {throw new NotFoundException("User is already following this user.");}

        user.get().getFollowed().add(userIdToFollow);
        userToFollow.get().getFollowers().add(UserId);


    }
    @Override
    public List<User> findAllByIdIn(List<Integer> userIds) {
        return userList.stream()
                .filter(user -> userIds.contains(user.getUserId()))
                .collect(Collectors.toList());
    }

}
