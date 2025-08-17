package org.example.clonezalo.controller.user;

import lombok.RequiredArgsConstructor;
import org.example.clonezalo.base.ResponseData;
import org.example.clonezalo.dto.auth.request.friendRRequest.FriendRRequest;
import org.example.clonezalo.dto.auth.response.friendRResponse.FriendRResponse;
import org.example.clonezalo.exception.NotFoundException;
import org.example.clonezalo.service.user.friendR.FriendRService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class FriendRController {
    private final FriendRService friendRService;
    @PostMapping("/makefriend")
    //gửi lời mời kết bạn
    public ResponseEntity<ResponseData<FriendRResponse>> makeFriend(@RequestBody FriendRRequest friendRRequest) {
        try {
            FriendRResponse friendRResponse = friendRService.makeFriend(friendRRequest);
            return ResponseEntity.ok(ResponseData.<FriendRResponse>builder()
                    .data(friendRResponse)
                    .status(200)
                    .message("Kết bạn thành công")
                    .build());
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseData.<FriendRResponse>builder()
                            .data(null)
                            .status(404)
                            .message(e.getMessage())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseData.<FriendRResponse>builder()
                            .data(null)
                            .status(500)
                            .message("Lỗi hệ thống: " + e.getMessage())
                            .build());
        }
    }
    //danh sách lời mời
    @GetMapping("/listfriend")
    List<FriendRResponse> friendInvitation(){
        return friendRService.friendInvitation();
    }
    //hàm này là có muốn nó thành bạn bè mình ko
    @PostMapping("/confirmInvitation")
    public FriendRResponse confirmInvitation(@RequestBody FriendRRequest friendRRequest){
        return friendRService.confirmInvitation(friendRRequest);
    }



}
