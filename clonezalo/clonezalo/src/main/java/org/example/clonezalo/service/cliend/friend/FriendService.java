package org.example.clonezalo.service.cliend.friend;

import org.example.clonezalo.dto.auth.response.friend.FriendResponse;

import java.util.List;

public interface FriendService {
    List<FriendResponse> getFriends();
}
