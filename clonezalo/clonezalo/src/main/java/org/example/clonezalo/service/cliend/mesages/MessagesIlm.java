package org.example.clonezalo.service.cliend.mesages;

import lombok.RequiredArgsConstructor;
import org.example.clonezalo.dto.auth.response.messages.request.MessagesRequest;
import org.example.clonezalo.dto.auth.response.messages.response.MessagesResponse;
import org.example.clonezalo.entity.Messages;
import org.example.clonezalo.entity.User;
import org.example.clonezalo.exception.NotFoundException;
import org.example.clonezalo.repository.MessagesRepository;
import org.example.clonezalo.repository.UserRepository;
import org.example.clonezalo.service.cloudinary.CloudinaryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessagesIlm implements MessagesService{
    private final UserRepository userRepository;
    private final MessagesRepository messagesRepository;
    private final CloudinaryService cloudinaryService;
    @Override
    public MessagesResponse sendMessages(MultipartFile file,Integer receiverId, MessagesRequest request) throws IOException {
        Messages messages=new Messages();
        String phoneSender= SecurityContextHolder.getContext().getAuthentication().getName();
        User userSender=userRepository.findByPhone(phoneSender).orElseThrow(()->new NotFoundException("ko có user sender này"));
        System.out.println(phoneSender);
        User userReceiver= userRepository.findById(receiverId).orElseThrow(()->new NotFoundException("ko co user receiver này"));
        System.out.println(userReceiver.getPhone());
            messages.setSender(userSender);
            messages.setReceiver(userReceiver);
            messages.setContent(request.getMessage());
            String imageUrl=uploadImage(file);
            messages.setImage_url(imageUrl);
            messages.setIs_seen(false);
            messagesRepository.save(messages);
            return mapToResponse(messages);
    }

    @Override
    public List<MessagesResponse> listMessages(Integer receiverId) {
        String phone=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByPhone(phone).orElseThrow(()->new NotFoundException("ko có user này"));
        List<Messages> messages=messagesRepository.findConversation(user,receiverId);
        return messages.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public MessagesResponse mapToResponse(Messages messages) {
        MessagesResponse response=new MessagesResponse();
        response.setUserSenderId(messages.getSender().getId());
        response.setUserReceiverId(messages.getReceiver().getId());
        response.setMessage(messages.getContent());
        response.setSeen(messages.getIs_seen());
        response.setImage( messages.getImage_url());
        response.setUserReceiverName(messages.getReceiver().getName());
        response.setUserSenderName(messages.getSender().getName());
        response.setImageReceiverName(messages.getSender().getImage());
        return response;
    }
    public String uploadImage(MultipartFile file) throws IOException {
        if(file==null||file.isEmpty()){
            return null;
        }
        String imageUrl=cloudinaryService.uploadFile(file);
        return imageUrl;
    }
}
