package org.example.clonezalo.service.cliend.user;

import org.example.clonezalo.dto.auth.response.user.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> finByPhone(String phone);
}
