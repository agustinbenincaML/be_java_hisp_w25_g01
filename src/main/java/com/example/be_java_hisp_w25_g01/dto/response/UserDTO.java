package com.example.be_java_hisp_w25_g01.dto.response;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.be_java_hisp_w25_g01.entity.User;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    Integer user_id;
    String user_name;
}
