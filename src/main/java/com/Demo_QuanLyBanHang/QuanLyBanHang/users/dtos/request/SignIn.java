package com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignIn {
    private String emailOrPhoneNumber;
    private String password;
}
