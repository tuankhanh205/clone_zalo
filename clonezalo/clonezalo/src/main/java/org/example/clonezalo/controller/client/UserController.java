package org.example.clonezalo.controller.client;

import lombok.RequiredArgsConstructor;
import org.example.clonezalo.dto.auth.response.user.response.UserResponse;
import org.example.clonezalo.service.cliend.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/fin-phone/{phone}")
    public List<UserResponse> finByPhone(@PathVariable("phone") String phone){
       return userService.finByPhone(phone);
    }
//    @GetMapping("/fin-phone")
//    public String finByPhone(){
//        return "dc ne";
//    }
}
