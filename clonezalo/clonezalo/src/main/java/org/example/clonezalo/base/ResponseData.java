package org.example.clonezalo.base;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData <T>{
    private int status;
    private String message;
    private T data;
}
