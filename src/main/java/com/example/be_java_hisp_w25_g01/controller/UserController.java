package com.example.be_java_hisp_w25_g01.controller;

import com.example.be_java_hisp_w25_g01.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g01.dto.response.UserDTO;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.repository.impl.UserRepositoryImpl;
import com.example.be_java_hisp_w25_g01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    //US 0001
    @PostMapping("/{UserId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@PathVariable int UserId, @PathVariable int userIdToFollow){
        return new ResponseEntity<>(userService.followUser(UserId,userIdToFollow),HttpStatus.OK);
    }


    //US 0002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> getFollowersCount(@PathVariable int userId){
        return new ResponseEntity<>(userService.getFollowersCount(userId), HttpStatus.OK);
    };


    //US 0003 Y 0008
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<List<FollowersDTO>> getSellerFollowers(@PathVariable int userId,
                                                                 @RequestParam(required = false) String order) {
        List<FollowersDTO> followers = userService.getFollowersList(userId);
        
        if ("asc".equalsIgnoreCase(order)) {
            followers.sort(Comparator.comparing(FollowersDTO::getUser_name));
        } else if ("desc".equalsIgnoreCase(order)) {
            followers.sort(Comparator.comparing(FollowersDTO::getUser_name).reversed());
        }

        return ResponseEntity.ok(followers);
    }

    //US 0004 Y 0008
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<List<UserDTO>> getFollowedSellers(@PathVariable int userId,
                                                            @RequestParam(required = false) String order) {
        List<UserDTO> lSellers = userService.getFollowedSellers(userId);

        if ("name_asc".equalsIgnoreCase(order)) {
            lSellers.sort(Comparator.comparing(UserDTO::getUser_name));
        } else if ("name_desc".equalsIgnoreCase(order)) {
            lSellers.sort(Comparator.comparing(UserDTO::getUser_name).reversed());
        }

        return ResponseEntity.ok(lSellers);
    }


    //US 0007
    @PostMapping("{UserId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<?> unfollowUser(@PathVariable int UserId, @PathVariable int userIdToUnfollow){
        return new ResponseEntity<>(userService.unfollowUser(UserId,userIdToUnfollow),HttpStatus.OK);
    };


}
