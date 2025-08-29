package com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUp {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String city; // Tỉnh hoặc thành phố
    private String district;
    private String address;
    private String password;
    private String confirmPassword;

}
