package org.example.clonezalo.dto.auth.request.friendRRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.clonezalo.base.enumEntity.FriendRStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendRRequest {
   private String sdt;
   private Integer senderId;
   private Integer receiver_id;
   private FriendRStatus status;
}
