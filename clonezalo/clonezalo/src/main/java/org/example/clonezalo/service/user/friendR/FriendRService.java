package org.example.clonezalo.service.user.friendR;

import org.example.clonezalo.dto.auth.request.friendRRequest.FriendRRequest;
import org.example.clonezalo.dto.auth.response.friendRResponse.FriendRResponse;

public interface FriendRService {
     FriendRResponse makeFriend(FriendRRequest friendRRequest);
     FriendRResponse friendInvitation();
     FriendRResponse confirmInvitation(FriendRRequest friendRRequest);
}
