package org.example.clonezalo.dto.auth.response.messages.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessagesResponse {
    private Integer userSenderId;
    private String userSenderName;
    private Integer userReceiverId;
    private String userReceiverName;
    private String message;
    private String image;
    private String imageReceiverName;
    private boolean isSeen;
}
