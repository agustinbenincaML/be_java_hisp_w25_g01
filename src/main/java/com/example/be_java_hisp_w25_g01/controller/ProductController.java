package com.example.be_java_hisp_w25_g01.controller;

import com.example.be_java_hisp_w25_g01.dto.request.PostDTO;
import com.example.be_java_hisp_w25_g01.dto.response.PostsListDTO;
import com.example.be_java_hisp_w25_g01.entity.Product;
import com.example.be_java_hisp_w25_g01.service.IPostService;
import com.example.be_java_hisp_w25_g01.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IPostService postService;


    @PostMapping("/post")
    public ResponseEntity<?> postProduct(@RequestBody PostDTO post){
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.OK);
    }

    //US 0006
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostsListDTO> listPosts(@PathVariable Integer userId){
        return new ResponseEntity<>(postService.getLastPostsFollowedBy(userId), HttpStatus.OK);
    }

}
