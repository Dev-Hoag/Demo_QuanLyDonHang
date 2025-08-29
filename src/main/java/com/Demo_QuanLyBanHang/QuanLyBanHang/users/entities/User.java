package com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private UUID userId;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 255)
    private String address;

    @Column(nullable = true, unique = true, length = 11)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @ManyToMany
    private Set<Role> roles;
}
