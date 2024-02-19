package com.example.be_java_hisp_w25_g01.service.impl;

import com.example.be_java_hisp_w25_g01.dto.request.PostDTO;
import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.dto.response.PostsListDTO;
import com.example.be_java_hisp_w25_g01.entity.Post;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.exception.BadRequestException;
import com.example.be_java_hisp_w25_g01.exception.NotFoundException;
import com.example.be_java_hisp_w25_g01.repository.IPostRepository;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.repository.impl.PostRepositoryImpl;
import com.example.be_java_hisp_w25_g01.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IUserRepository userRepository;


    @Override
    public PostsListDTO getLastPostsFollowedBy(Integer userId){
        //Buscar usuario sino tirar excepcion
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("No se encontró el usuario con el id " + userId));

        //Lista de ids que sigue el usuario
        List<Integer> followedList = user.getFollowed();

        //Todos los usuarios
        List<User> allUsers = userRepository.findAll();

        //Todos los usuarios que sigue el user...
        List<User> usersFollowed = allUsers.stream()
                .filter(u -> followedList.contains(u.getUserId()))
                .toList();

        //Todos los posts de los usuarios que sigue
        List<Post> post = usersFollowed.stream()
                .flatMap(u -> u.getPosts().stream())
                .toList();

        return null;
    }

    @Override
    public MessagesDTO followUser(int userId) {
        return null;
    }

    @Override
    public MessagesDTO createPost(PostDTO post){
        if(validatePostDto(post)){
            throw new BadRequestException("Post no creado");
        }
        return new MessagesDTO("Post creado exitosamente");
    }

    private boolean validatePostDto(PostDTO post){

        return true;
    }


}
