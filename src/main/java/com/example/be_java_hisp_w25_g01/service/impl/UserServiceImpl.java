package com.example.be_java_hisp_w25_g01.service.impl;

import com.example.be_java_hisp_w25_g01.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g01.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g01.dto.response.MessagesDTO;
import com.example.be_java_hisp_w25_g01.dto.response.UserDTO;
import com.example.be_java_hisp_w25_g01.entity.User;
import com.example.be_java_hisp_w25_g01.exception.NotFoundException;
import com.example.be_java_hisp_w25_g01.repository.IUserRepository;
import com.example.be_java_hisp_w25_g01.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    public IUserRepository userRepository;


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
                user.get().getFollowers() != null ? (long) user.get().getFollowers().size() : 0
        );
    }
    @Override
    public List<FollowersDTO> getFollowersList(Integer userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isEmpty()) {throw new NotFoundException("User with id: " + userId + " not found.");}

        List<User> followers = userRepository.findAllByIdIn(user.get().getFollowers());

        return FollowersDTO.convertToFollowersDTOList(user, followers);
    }
    @Override
    public FollowersDTO getFollowedList(Integer userId) {
        return null;
    }

    @Override
    public MessagesDTO followUser(Integer UserId, Integer userIdToFollow) {
        userRepository.followUser(UserId, userIdToFollow);
        return new MessagesDTO("User with id: " + UserId + " is now following user with id: " + userIdToFollow);

    }

    @Override
    public MessagesDTO unfollowUser(Integer UserId, Integer userIdToUnfollow) {
        userRepository.unfollowUser(UserId, userIdToUnfollow);
        return new MessagesDTO("User with id: " + UserId + " is now unfollowing user with id: " + userIdToUnfollow);
    }

    /*
    * Obtener un listado de todos los vendedores a los cuales sigue un
    determinado usuario (¿A quién sigo?)
    * un usuario es vendedor si tiene posts*/
    public List<UserDTO> getFollowedSellers(int userId){
        Optional<User> users = userRepository.findById(userId);
        if (users.isPresent()){
            User user = users.get();

            List<User> followedUsers = userRepository.findAllByIdIn(user.getFollowed());
            List<UserDTO> userDTOList = UserDTO.convertToDTOList(followedUsers);

            return userDTOList;
        }else{
            throw new NotFoundException("Usuario no encontrado con el ID: " + userId);
        }
    }

    private UserDTO convertUserToDto(User u){
        return new UserDTO(
                u.getUserId(),
                u.getUserName());
    }


}
