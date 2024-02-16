package com.example.be_java_hisp_w25_g01.service.impl;

import com.example.be_java_hisp_w25_g01.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g01.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.dto.response.UserDTO;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.exception.NotFoundException;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.repository.impl.UserRepositoryImpl;
import com.example.be_java_hisp_w25_g01.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    public IUserRepository userRepository;
    public UserServiceImpl(UserRepositoryImpl userRepository) {this.userRepository = userRepository;}

    @Override
    public FollowersCountDTO getFollowersCount(Integer userId){
        Optional<User> user = this.userRepository.findById(userId);
        if(user.isEmpty()) {throw new NotFoundException("User with id: " + userId + " not found.");}

        

        return null;
    }
    @Override
    public FollowersDTO getFollowersList(Integer userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isEmpty()) {throw new NotFoundException("User with id: " + userId + " not found.");}

        

        return null;
    }

    @Override
    public FollowersDTO getFollowedList(Integer userId) {
        return null;
    }

    @Override
    public MessagesDTO followUser(Integer UserId, Integer userIdToFollow) {
        return null;
    }

    /*
    * Obtener un listado de todos los vendedores a los cuales sigue un
    determinado usuario (¿A quién sigo?)
    * un usuario es vendedor si tiene posts*/
    public List<UserDTO> getFollowedSellers(int userId){
        List<User> lUsers = userRepository.findAll();
        List<User> sellers = lUsers.stream()
                .filter(user -> user.getPosts() != null && !user.getPosts().isEmpty())
                .collect(Collectors.toList());
        return null;
    }
}
