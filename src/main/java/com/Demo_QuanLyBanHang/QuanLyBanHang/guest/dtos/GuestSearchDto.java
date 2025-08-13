package com.Demo_QuanLyBanHang.QuanLyBanHang.guest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestSearchDto {
    
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
} 