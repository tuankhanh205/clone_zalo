package org.example.clonezalo.service.cliend.user;

import lombok.RequiredArgsConstructor;
import org.example.clonezalo.dto.auth.request.user.request.UserRequest;
import org.example.clonezalo.dto.auth.response.user.response.UserResponse;
import org.example.clonezalo.entity.FriendR;
import org.example.clonezalo.entity.User;
import org.example.clonezalo.exception.NotFoundException;
import org.example.clonezalo.repository.UserRepository;
import org.example.clonezalo.service.cloudinary.CloudinaryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserIlm implements UserService{
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;
    @Override
    public List<UserResponse> finByPhone(String phone) {
        String phoneH= SecurityContextHolder.getContext().getAuthentication().getName();
        User userH=userRepository.findByPhone(phoneH).orElseThrow(()->new NotFoundException("ko co phone nay"));

        List<User> user = userRepository.findAll().stream().filter(n->n.getPhone().equals(phone))
                .filter(n->!n.getId().equals(userH.getId())).collect(Collectors.toList());
        return user.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public UserResponse finById(Integer id) {
        User user=userRepository.findById(id).orElseThrow(()->new NotFoundException("ko co user nay"));
        return mapToResponse(user);
    }

    @Override
    public UserResponse update(UserRequest userRequest, MultipartFile image) {
        String phone=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByPhone(phone).orElseThrow(()->new NotFoundException("ko co phone nay"));
        user.setName(userRequest.getUserName());
        user.setSex(userRequest.getSex());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setImage(cloudinaryService.uploadFile(image));
        return mapToResponse(userRepository.save(user));
    }

    public UserResponse mapToResponse(User user) {
        String phoneH = SecurityContextHolder.getContext().getAuthentication().getName();
        User userH = userRepository.findByPhone(phoneH)
                .orElseThrow(() -> new NotFoundException("ko co phone nay"));

        UserResponse userResponse = new UserResponse();
        String status = "Người lạ";

        // check receiver
        if (user.getReceiver() != null && !user.getReceiver().isEmpty()) {
            for (FriendR friendR : user.getReceiver()) {
                if (friendR.getUserSender().getId().equals(userH.getId())) {
                    status = friendR.getStatus().name();
                    break; // tìm thấy thì thoát luôn
                }
            }
        }

        // check sender
        if (status.equals("Người lạ") && user.getSender() != null && !user.getSender().isEmpty()) {
            for (FriendR friendR : user.getSender()) {
                if (friendR.getUserReceiver().getId().equals(userH.getId())) {
                    status = friendR.getStatus().name();
                    break;
                }
            }
        }

        userResponse.setId(user.getId());
        userResponse.setSex(user.getSex().name());
        userResponse.setDateOfBirth(user.getDateOfBirth());
        userResponse.setStatus(status);
        userResponse.setImage(user.getImage());
        userResponse.setUserName(user.getName());
        userResponse.setPhone(user.getPhone());
        return userResponse;
    }

}
