package org.example.clonezalo.dto.auth.response.user.response;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Integer id;
    private String userName;
    private String image;
    private String phone;
}
