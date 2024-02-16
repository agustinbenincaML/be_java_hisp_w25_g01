package com.example.be_java_hisp_w25_g01.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDTO {
    private Integer user_id;
    private String user_name;
    List<User>

}
