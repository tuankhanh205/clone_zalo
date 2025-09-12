package org.example.clonezalo.service.cliend.user;

import lombok.RequiredArgsConstructor;
import org.example.clonezalo.dto.auth.response.user.response.UserResponse;
import org.example.clonezalo.entity.User;
import org.example.clonezalo.exception.NotFoundException;
import org.example.clonezalo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserIlm implements UserService{
    private final UserRepository userRepository;
    @Override
    public List<UserResponse> finByPhone(String phone) {
        String phoneH= SecurityContextHolder.getContext().getAuthentication().getName();
        User userH=userRepository.findByPhone(phoneH).orElseThrow(()->new NotFoundException("ko co phone nay"));

        List<User> user = userRepository.findAll().stream().filter(n->n.getPhone().contains(phone))
                .filter(n->!n.getId().equals(userH.getId())).collect(Collectors.toList());
        return user.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    public UserResponse mapToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setImage(user.getImage());
        userResponse.setUserName(user.getName());
        userResponse.setPhone(user.getPhone());
        return userResponse;
    }
}
