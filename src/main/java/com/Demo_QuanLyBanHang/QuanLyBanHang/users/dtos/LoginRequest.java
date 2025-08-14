package com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
