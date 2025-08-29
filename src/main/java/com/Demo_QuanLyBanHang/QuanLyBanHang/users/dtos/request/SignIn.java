package com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignIn {
    private String email;
    private String phoneNumber;
    private String password;
}
