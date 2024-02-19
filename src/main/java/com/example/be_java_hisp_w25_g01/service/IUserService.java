package com.example.be_java_hisp_w25_g01.service;

import com.example.be_java_hisp_w25_g01.dto.response.*;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAll();
    List<FollowersDTO> getFollowersList(Integer userId);
    FollowersCountDTO  getFollowersCount(Integer userId);
    MessagesDTO followUser (Integer UserId,Integer userIdToFollow );

    MessagesDTO unfollowUser (Integer UserId,Integer userIdToUnfollow );

    List<FollowedDTO> getFollowedList(Integer userId);



}
