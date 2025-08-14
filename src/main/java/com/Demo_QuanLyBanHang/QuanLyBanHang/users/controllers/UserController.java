package com.Demo_QuanLyBanHang.QuanLyBanHang.users.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE', 'USER')")
public class UserController {

    @GetMapping("/dashboard")
    public String userDashboard() {
        return "Welcome User!";
    }
}
