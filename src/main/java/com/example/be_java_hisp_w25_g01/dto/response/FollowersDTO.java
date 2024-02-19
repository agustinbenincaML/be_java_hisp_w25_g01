package com.example.be_java_hisp_w25_g01.dto.response;

import com.example.be_java_hisp_w25_g01.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class FollowersDTO {
    //US 0003
    Integer user_id;
    String user_name;
    List<UserDTO> followers;


    public static List<FollowersDTO> convertToFollowersDTOList(Optional<User> user, List<User> userList) {
        List<FollowersDTO> followersDTOList = new ArrayList<>();

        FollowersDTO followersDTO = new FollowersDTO();
        followersDTO.setUser_id(user.get().getUserId());
        followersDTO.setUser_name(user.get().getUserName());
        followersDTO.setFollowers(UserDTO.convertToDTOList(userList));
        followersDTOList.add(followersDTO);

        return followersDTOList;


    }
}
