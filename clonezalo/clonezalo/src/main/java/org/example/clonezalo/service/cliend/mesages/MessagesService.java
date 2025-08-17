package org.example.clonezalo.service.user.mesages;

import org.example.clonezalo.dto.auth.response.messages.request.MessagesRequest;
import org.example.clonezalo.dto.auth.response.messages.response.MessagesResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MessagesService {
    MessagesResponse sendMessages(MultipartFile file,Integer receiverId, MessagesRequest request) throws IOException;
    List<MessagesResponse> listMessages(Integer receiverId);
}
