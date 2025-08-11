package com.Demo_QuanLyBanHang.QuanLyBanHang.users.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
public class EmployeeController {

    @GetMapping("/dashboard")
    public String employeeDashboard() {
        return "Welcome Employee!";
    }
}