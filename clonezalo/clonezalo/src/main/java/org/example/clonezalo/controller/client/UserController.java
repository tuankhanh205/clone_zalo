package org.example.clonezalo.controller.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.example.clonezalo.dto.auth.request.user.request.UserRequest;
import org.example.clonezalo.dto.auth.response.user.response.UserResponse;
import org.example.clonezalo.service.cliend.user.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @GetMapping("/{id}")
    UserResponse finById(@PathVariable("id") Integer id){
        return userService.finById(id);
    }
    @PutMapping(value = "/update-user", consumes = {"multipart/form-data"})
    public UserResponse update(
            @RequestPart("userRequest") String userRequestJson,
            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        UserRequest userRequest = mapper.readValue(userRequestJson, UserRequest.class);
        return userService.update(userRequest, image);
    }


//    @GetMapping("/fin-phone")
//    public String finByPhone(){
//        return "dc ne";
//    }
}
