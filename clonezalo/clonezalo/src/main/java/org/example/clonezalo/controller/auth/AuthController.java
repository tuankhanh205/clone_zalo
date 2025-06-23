package org.example.clonezalo.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.clonezalo.exception.NotFoundException;
import org.example.clonezalo.base.ResponseData;
import org.example.clonezalo.dto.auth.request.LoginRequest;
import org.example.clonezalo.dto.auth.request.RegisterRequest;
import org.example.clonezalo.dto.auth.response.AuthResponse;
import org.example.clonezalo.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<ResponseData<AuthResponse.UserInfo>> register(@Valid @RequestBody RegisterRequest registerRequest){

       try {
           AuthResponse.UserInfo userInfo = authService.register(registerRequest);

           return ResponseEntity.ok(ResponseData.<AuthResponse.UserInfo>builder()
                           .status(200)
                   .message("tạo tài khoản thành công")
                   .data(userInfo).build());

       }
       catch (NotFoundException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                   ResponseData.<AuthResponse.UserInfo>builder().message(e.getMessage())
                           .data(null)
                           .status(404).build()
           );

       }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseData<AuthResponse>> login(@RequestBody LoginRequest loginRequest){

            AuthResponse authResponse=authService.login(loginRequest);
           return  ResponseEntity.ok(ResponseData.<AuthResponse>builder().data(authResponse)
                    .status(200)
                    .message("đăng nhâpj thành công").build());


    }
}
