package org.example.clonezalo.dto.auth.response.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FriendResponse {
    private Integer id;
    private String name;
    private String image;
}
