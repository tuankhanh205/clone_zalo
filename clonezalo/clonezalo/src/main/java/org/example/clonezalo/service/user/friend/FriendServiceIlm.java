package org.example.clonezalo.service.user.friend;

import lombok.RequiredArgsConstructor;
import org.example.clonezalo.dto.auth.response.friend.FriendResponse;
import org.example.clonezalo.entity.Friend;
import org.example.clonezalo.entity.User;
import org.example.clonezalo.exception.NotFoundException;
import org.example.clonezalo.repository.FriendRepository;
import org.example.clonezalo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class FriendServiceIlm implements FriendService{
private final FriendRepository friendRepository;
private final UserRepository userRepository;
    @Override
    public List<FriendResponse> getFriends() {
        String phone= SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByPhone(phone).orElseThrow(()-> new NotFoundException("ko có user nay"));
        List<Friend> friends=friendRepository.findByUser1(user);
        return friends.stream().map(this::mapToResponse).collect(Collectors.toList());
    }
    public FriendResponse mapToResponse(Friend friend){
        FriendResponse friendResponse=new FriendResponse();
        friendResponse.setId(friend.getId());
        friendResponse.setName(friend.getUser2().getName());
        return friendResponse;
    }
}
