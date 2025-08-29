package com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private UUID id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private Set<RoleResponse> roles;
}
