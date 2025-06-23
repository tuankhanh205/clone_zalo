package org.example.clonezalo.dto.auth.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Không được để trống số điện thoại")
    @Size(min = 10, max = 11, message = "Số điện thoại phải từ 10 đến 11 số")
    private String phone;

    @NotEmpty(message = "Không được để trống mật khẩu")
    @Size(min = 6, max = 17, message = "Mật khẩu phải từ 6 đến 17 ký tự")
    private String password;

    @NotEmpty(message = "Không được để trống tên")
    @Size(min = 6, max = 30, message = "Tên phải từ 6 đến 30 ký tự")
    private String name;
}
