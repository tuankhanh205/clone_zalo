package org.example.clonezalo.dto.auth.response.friendRResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendRResponse {
    private Integer id;
    private String senderName;
    private String receiveName;
    private String status;
}
