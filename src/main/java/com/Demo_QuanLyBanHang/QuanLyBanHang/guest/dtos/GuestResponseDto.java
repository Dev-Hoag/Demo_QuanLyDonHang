package com.Demo_QuanLyBanHang.QuanLyBanHang.guest.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.entities.Guest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestResponseDto {
    
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private Guest.GuestType guestType;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public static GuestResponseDto fromEntity(Guest guest) {
        return new GuestResponseDto(
            guest.getId(),
            guest.getFullName(),
            guest.getPhoneNumber(),
            guest.getEmail(),
            guest.getAddress(),
            guest.getGuestType(),
            guest.getIsActive(),
            guest.getCreatedAt(),
            guest.getUpdatedAt()
        );
    }
} 