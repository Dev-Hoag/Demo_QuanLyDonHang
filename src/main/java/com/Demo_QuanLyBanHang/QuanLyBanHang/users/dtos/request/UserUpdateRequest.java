package com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private String address;
    private List<String> roles;
}
