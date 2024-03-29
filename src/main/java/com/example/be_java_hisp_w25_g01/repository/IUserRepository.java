package com.example.be_java_hisp_w25_g01.repository;

import com.example.be_java_hisp_w25_g01.entity.Post;
import com.example.be_java_hisp_w25_g01.entity.User;

import java.util.List;
import java.util.Optional;
public interface IUserRepository {

    List<User> findAll();

    Optional<User> findById(Integer userId);

    public void followUser(Integer UserId, Integer userIdToFollow);

    public void unfollowUser(Integer UserId, Integer userIdToUnfollow);

    public void createPost(Post post);
    List<User> findAllByIdIn(List<Integer> userIds);
}
