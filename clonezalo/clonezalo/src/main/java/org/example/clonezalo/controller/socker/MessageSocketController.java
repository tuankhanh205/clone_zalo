//package org.example.clonezalo.controller.socker;
//
//import lombok.RequiredArgsConstructor;
//import org.example.clonezalo.dto.auth.response.messages.response.MessagesResponse;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//@Controller
//@RequiredArgsConstructor
//public class MessageSocketController {
//    private final SimpMessagingTemplate messagingTemplate;
//
//    public void sendMessageToUser(Integer receiverId, MessagesResponse response) {
//        // Gửi message đến client qua topic riêng
//        messagingTemplate.convertAndSend("/topic/messages/" + receiverId, response);
//    }
//}
