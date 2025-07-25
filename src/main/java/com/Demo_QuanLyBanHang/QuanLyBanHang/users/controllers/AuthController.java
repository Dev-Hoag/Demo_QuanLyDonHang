package com.Demo_QuanLyBanHang.QuanLyBanHang.users.controllers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.RegisterRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.LoginRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
