package com.Demo_QuanLyBanHang.QuanLyBanHang.guest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "guests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;
    
    @Column(name = "phone_number", nullable = false, unique = true, length = 15)
    private String phoneNumber;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "address", nullable = false, length = 500)
    private String address;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "guest_type", nullable = false)
    private GuestType guestType; // SENDER hoặc RECEIVER
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
    
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    public enum GuestType {
        SENDER, RECEIVER
    }

    @Column(name = "users_id", nullable = false, unique = true)
    private UUID userId;
} 