package org.example.clonezalo.service.cliend.friendR;

import org.example.clonezalo.dto.auth.request.friendRRequest.FriendRRequest;
import org.example.clonezalo.dto.auth.response.friendRResponse.FriendRResponse;

import java.util.List;

public interface FriendRService {
     FriendRResponse makeFriend(FriendRRequest friendRRequest);
     List<FriendRResponse> friendInvitation();
     FriendRResponse confirmInvitation(FriendRRequest friendRRequest);
}
