package org.example.clonezalo.dto.auth.response.user.response;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private String status;
    private String sex;
    private LocalDate dateOfBirth;
}
