package com.Demo_QuanLyBanHang.QuanLyBanHang.guest.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.guest.entities.Guest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestResponseDto {
    
    private UUID id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private Guest.GuestType guestType;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID userId;
    
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
            guest.getUpdatedAt(),
            guest.getUserId()
        );
    }
} 