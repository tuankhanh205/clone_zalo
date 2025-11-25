package org.example.clonezalo.controller.socker;

import lombok.RequiredArgsConstructor;
import org.example.clonezalo.dto.auth.response.messages.response.MessagesResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class MessageSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat") // client gửi tới /app/chat
    public void sendMessageToUser(MessagesResponse message) {
        Integer receiverId = message.getUserReceiverId(); // lấy ID người nhận từ payload
        // Gửi message đến client theo topic riêng
        messagingTemplate.convertAndSend("/topic/messages/" + receiverId, message);
    }
}
