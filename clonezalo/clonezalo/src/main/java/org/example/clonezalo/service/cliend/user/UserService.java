package org.example.clonezalo.service.cliend.user;

import org.example.clonezalo.dto.auth.request.user.request.UserRequest;
import org.example.clonezalo.dto.auth.response.user.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    List<UserResponse> finByPhone(String phone);
    UserResponse finById(Integer id);
    UserResponse update(UserRequest userRequest, MultipartFile image);
}
