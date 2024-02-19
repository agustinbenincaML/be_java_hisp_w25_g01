package com.example.be_java_hisp_w25_g01.controller;

import com.example.be_java_hisp_w25_g01.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getAll")
    public  ResponseEntity<?> getAll(){
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    //US 0001
    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@PathVariable int UserId, @PathVariable int userIdToFollow){
        return new ResponseEntity<>(userService.followUser(UserId,userIdToFollow),HttpStatus.OK);
    };


    //US 0002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> getFollowersCount(@PathVariable int userId){return null;};


    //US 0003
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<List<FollowersDTO>> getSellerFollowers(@PathVariable int userId){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //US 0004
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<?> getFollowedSellers(@PathVariable int userId){
            return ResponseEntity.ok().build();
    }
/*
    //US 008
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<List<FollowersDTO>> getFollowerList(@PathVariable int userId){
        return ResponseEntity.ok().build();

    }
*/


}
