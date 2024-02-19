package com.example.be_java_hisp_w25_g01.controller;

import com.example.be_java_hisp_w25_g01.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g01.dto.response.UserDTO;
import com.example.be_java_hisp_w25_g01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
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
    @PostMapping("/{UserId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followUser(@PathVariable int UserId, @PathVariable int userIdToFollow){
        return new ResponseEntity<>(userService.followUser(UserId,userIdToFollow),HttpStatus.OK);
    };


    //US 0002
    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<?> getFollowersCount(@PathVariable int userId){
        return new ResponseEntity<>(userService.getFollowersCount(userId), HttpStatus.OK);
    };


    //US 0003
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<List<FollowersDTO>> getSellerFollowers(@PathVariable int userId,
                                                @RequestParam(required = false) String ordenamiento) {
        List<FollowersDTO> followers = (List<FollowersDTO>) userService.getFollowersList(userId);

        if ("asc".equalsIgnoreCase(ordenamiento)) {
            followers.sort(Comparator.comparing(FollowersDTO::getUser_name));
        } else if ("desc".equalsIgnoreCase(ordenamiento)) {
            followers.sort(Comparator.comparing(FollowersDTO::getUser_name).reversed());
        }

        return ResponseEntity.ok(followers);
    }

    //US 0004
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<List<UserDTO>> getFollowedSellers(@PathVariable int userId,
                                                            @RequestParam(required = false) String ordenamiento) {
        List<UserDTO> lSellers = userService.getFollowedSellers(userId);

        if ("asc".equalsIgnoreCase(ordenamiento)) {
            lSellers.sort(Comparator.comparing(UserDTO::getUser_name));
        } else if ("desc".equalsIgnoreCase(ordenamiento)) {
            lSellers.sort(Comparator.comparing(UserDTO::getUser_name).reversed());
        }

        return ResponseEntity.ok(lSellers);
    }


}
