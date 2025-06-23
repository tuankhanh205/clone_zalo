package org.example.clonezalo.service.auth;

import org.example.clonezalo.dto.auth.request.LoginRequest;
import org.example.clonezalo.dto.auth.request.RegisterRequest;
import org.example.clonezalo.dto.auth.response.AuthResponse;
import org.springframework.stereotype.Service;


public interface AuthService {
    AuthResponse.UserInfo register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
