package org.example.clonezalo.service.auth;


import lombok.RequiredArgsConstructor;
import org.example.clonezalo.exception.NotFoundException;
import org.example.clonezalo.base.enumEntity.RoleEnum;
import org.example.clonezalo.dto.auth.request.LoginRequest;
import org.example.clonezalo.dto.auth.request.RegisterRequest;
import org.example.clonezalo.dto.auth.response.AuthResponse;

import org.example.clonezalo.entity.User;

import org.example.clonezalo.repository.UserRepository;
import org.example.clonezalo.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
    private final JwtService service;
    @Override
    public AuthResponse.UserInfo register(RegisterRequest registerRequest) {
        validate(registerRequest);
        String encodePassWord=maHoaMk(registerRequest.getPassword());
        User user=saveUser(registerRequest,encodePassWord);
        return new AuthResponse.UserInfo().builder().phone(user.getPhone()).
                role(user.getRole().name()).
               name(user.getName()).build();
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user=userRepository.findByPhone(loginRequest.getPhone()).orElseThrow(()->new NotFoundException("ko co user nay"));
        if(!loginRequest.getPhone().isEmpty()&&!loginRequest.getPassWord().isEmpty()) {
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
           boolean ss=encoder.matches(loginRequest.getPassWord(),user.getPassword());
           if(user.getPhone().equals(loginRequest.getPhone())&&ss){
                   String accessToken=service.generateAccessToken(user.getPhone(),user.getRole().name());
                   String refreshToken=service.generateRefreshToken(user.getName());
                   AuthResponse.UserInfo userInfo=new AuthResponse.UserInfo().builder().role(user.getRole().name())
                           .phone(user.getPhone())
                           .name(user.getName()).build();
                   return new AuthResponse().builder().accessToken(accessToken).refreshToken(refreshToken)
                           .user(userInfo).build();

           }
           else{
               throw new RuntimeException("sai tài khoản hoặc mật khẩu");
           }

        }else{
            throw new RuntimeException("ko được để trống sdt và mật khẩu");
        }

    }

    public void validate(RegisterRequest registerRequest){


        if(userRepository.countByPhone(registerRequest.getPhone())>0){
            throw new NotFoundException("Phone number tồn tại");
        }


    }
    public String maHoaMk(String mk){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(mk);
    }
    public User saveUser(RegisterRequest registerRequest,String encodePassWord){
        User user=new User();
        user.setPhone(registerRequest.getPhone());
        user.setPassword(encodePassWord);
        user.setName(registerRequest.getName());
        user.setRole(RoleEnum.USER);
        return userRepository.save(user);
    }
}
