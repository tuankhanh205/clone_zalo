package org.example.clonezalo.controller.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
//import org.example.clonezalo.controller.socker.MessageSocketController;
import org.example.clonezalo.controller.socker.MessageSocketController;
import org.example.clonezalo.dto.auth.response.messages.request.MessagesRequest;
import org.example.clonezalo.dto.auth.response.messages.response.MessagesResponse;
import org.example.clonezalo.service.cliend.mesages.MessagesService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class MessagesController {
    private final MessagesService messagesService;
    private final MessageSocketController messageSocketController;
    @PostMapping(value ="/messages" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MessagesResponse sendMessages(
            @RequestPart(value = "file", required = false) MultipartFile file,
            @RequestParam("receiverId") Integer receiverId,
            @RequestPart("request") String requestJson   // nhận JSON dạng String
    ) throws IOException {

        // Nếu muốn, parse JSON thành MessagesRequest
        ObjectMapper objectMapper = new ObjectMapper();
        MessagesRequest request = objectMapper.readValue(requestJson, MessagesRequest.class);
        MessagesResponse messagesResponse = messagesService.sendMessages(file, receiverId, request);
        messageSocketController.sendMessageToUser(messagesResponse);
        return messagesResponse;
    }

    @GetMapping ("/list/messages/{id}")
    List<MessagesResponse> listMessages(@PathVariable("id") Integer receiverId){
        return messagesService.listMessages(receiverId);

    }

}
