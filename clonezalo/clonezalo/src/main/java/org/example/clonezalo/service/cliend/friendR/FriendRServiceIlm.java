package org.example.clonezalo.service.cliend.friendR;

import lombok.RequiredArgsConstructor;
import org.example.clonezalo.base.enumEntity.FriendRStatus;
import org.example.clonezalo.dto.auth.request.friendRRequest.FriendRRequest;
import org.example.clonezalo.dto.auth.response.friendRResponse.FriendRResponse;
import org.example.clonezalo.entity.Friend;
import org.example.clonezalo.entity.FriendR;
import org.example.clonezalo.repository.FriendRepository;
import org.example.clonezalo.entity.User;
import org.example.clonezalo.exception.NotFoundException;
import org.example.clonezalo.repository.FriendRRepository;
import org.example.clonezalo.repository.UserRepository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendRServiceIlm implements FriendRService{
    private final FriendRRepository friendRRepository;
    private final UserRepository userRepository;
    private final FriendRepository friendRepository;


    @Override
    public FriendRResponse makeFriend(FriendRRequest friendRRequest) {
        FriendR friendR=new FriendR();
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();

        User sender=userRepository.findByPhone(phone).orElseThrow(()->new NotFoundException("ko có user gửi này"));
        User receiver=userRepository.findByPhone(friendRRequest.getSdt()).orElseThrow(()->new NotFoundException("ko có user nhận này"));
        if(!friendRRepository.findByUserSenderAndUserReceiver(sender,receiver).isPresent()) {
            friendR.setStatus(FriendRStatus.PENDING);
            friendR.setUserSender(sender);
            friendR.setUserReceiver(receiver);
            friendRRepository.save(friendR);
        }
        else
        throw new NotFoundException("đã gửi lời mời trước đó");


        return mapToResponse(friendR);

    }

    @Override
    public List<FriendRResponse> friendInvitation() {
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        User receiver =userRepository.findByPhone(phone).orElseThrow(()->new NotFoundException("ko có user này"));
        List<FriendR> users=friendRRepository.findByUserReceiver(receiver,FriendRStatus.PENDING);
        return users.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public FriendRResponse confirmInvitation(FriendRRequest friendRRequest) {
        Friend friend=new Friend();
        Friend friend1=new Friend();
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        User use1 =userRepository.findByPhone(phone).orElseThrow(()->new NotFoundException("ko có user nay"));
        User use2 =userRepository.findByPhone(friendRRequest.getSdt()).orElseThrow(()->new NotFoundException("ko có user nay"));
        List<FriendR> friendRS=friendRRepository.findByUserReceiver(use1,FriendRStatus.PENDING);

        List<Friend> friends=new ArrayList<>();
        for(FriendR friendR:friendRS) {
            if(friendRRequest.getStatus().equals(FriendRStatus.ACCEPTED)) {
                friendR.setStatus(FriendRStatus.ACCEPTED);
                friend.setUser1(use1);
                friend.setUser2(use2);
                friends.add(friend);
                friend1.setUser2(use1);
                friend1.setUser1(use2);
                friends.add(friend1);
                friendRepository.saveAll(friends);

                friendRRepository.save(friendR);
            } else if (friendRRequest.getStatus().equals(FriendRStatus.DECLINED)) {
                friendR.setStatus(FriendRStatus.DECLINED);
                friendRRepository.save(friendR);
            }
            else{
                throw new NotFoundException("ko co trang thai nay");
            }

        }
        return friendRS.stream().map(this::mapToResponse).findFirst().orElse(null);
    }

    public FriendRResponse mapToResponse(FriendR friendR) {
        StringBuilder stringBuilder=new StringBuilder();
        FriendRResponse friendRResponse=new FriendRResponse();
        friendRResponse.setId(friendR.getId());
        friendRResponse.setReceiveName(friendR.getUserReceiver().getName());
        friendRResponse.setSenderName(friendR.getUserSender().getName());
        friendRResponse.setImage(friendR.getUserSender().getImage());

        String senAtString=String.valueOf(friendR.getSentAt());
        senAtString=senAtString.substring(0,10);
        friendRResponse.setSentAt(senAtString);
        if(friendR.getStatus().equals(FriendRStatus.PENDING)) {
            friendRResponse.setStatus("đang chờ");
        }
        else if(friendR.getStatus().equals(FriendRStatus.ACCEPTED)) {
            friendRResponse.setStatus("đã chấp nhận");
        }
        else{
            friendRResponse.setStatus("ko chấp nhận");
        }
        return friendRResponse;
    }
}
