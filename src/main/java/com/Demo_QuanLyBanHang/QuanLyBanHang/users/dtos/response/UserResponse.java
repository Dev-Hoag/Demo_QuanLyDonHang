package com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.Role;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private UUID userId;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private Set<Role> roles;
}
