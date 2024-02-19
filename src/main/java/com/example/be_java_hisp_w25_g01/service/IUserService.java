package com.example.be_java_hisp_w25_g01.service;

import com.example.be_java_hisp_w25_g01.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g01.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.dto.response.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAll();
    List<FollowersDTO> getFollowersList(Integer userId);
    FollowersDTO getFollowedList(Integer userId);
    FollowersCountDTO  getFollowersCount(Integer userId);

    MessagesDTO followUser (Integer UserId,Integer userIdToFollow );

    MessagesDTO unfollowUser (Integer UserId,Integer userIdToUnfollow );

    List<UserDTO> getFollowedSellers(int userId);


}
