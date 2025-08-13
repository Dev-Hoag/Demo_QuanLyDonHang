package com.Demo_QuanLyBanHang.QuanLyBanHang.guest.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.entities.Guest;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestCreateDto {
    
    @NotBlank(message = "Họ tên không được để trống")
    @Size(min = 2, max = 100, message = "Họ tên phải từ 2 đến 100 ký tự")
    private String fullName;
    
    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0|84)(3[2-9]|5[689]|7[06-9]|8[1-689]|9[0-46-9])[0-9]{7}$", 
             message = "Số điện thoại không hợp lệ")
    private String phoneNumber;
    
    @Email(message = "Email không hợp lệ")
    @Size(max = 100, message = "Email không được quá 100 ký tự")
    private String email;
    
    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(min = 10, max = 500, message = "Địa chỉ phải từ 10 đến 500 ký tự")
    private String address;
    
    @NotNull(message = "Loại khách hàng không được để trống")
    private Guest.GuestType guestType;
} 