package org.example.clonezalo.controller.client;

import lombok.RequiredArgsConstructor;
import org.example.clonezalo.dto.auth.response.friend.FriendResponse;
import org.example.clonezalo.service.cliend.friend.FriendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;
        @GetMapping("/friendsList")
    List<FriendResponse> getFriends(){
        return friendService.getFriends();
    }

}
