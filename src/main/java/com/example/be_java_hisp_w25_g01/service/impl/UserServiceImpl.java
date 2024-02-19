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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    public IUserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::convertUserToDto)
                .toList();
    }
    @Override
    public FollowersCountDTO getFollowersCount(Integer userId){
        Optional<User> user = this.userRepository.findById(userId);
        if(user.isEmpty()) {throw new NotFoundException("User with id: " + userId + " not found.");}
        return new FollowersCountDTO(
                user.get().getUserId(),
                user.get().getUserName(),
                (long) user.get().getFollowers().size()
        );
    }
    @Override
    public FollowersDTO getFollowersList(Integer userId) {
        Optional<User> userList = this.userRepository.findById(userId);
        if (userList.isEmpty()) {throw new NotFoundException("User with id: " + userId + " not found.");}
        List<User> followers = userList.get().getFollowers();
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
        if (sellers.isEmpty()){
            return null;
        }
        return null;
    }

    private UserDTO convertUserToDto(User u){
        return new UserDTO(
                u.getUserId(),
                u.getUserName());
    }


}
