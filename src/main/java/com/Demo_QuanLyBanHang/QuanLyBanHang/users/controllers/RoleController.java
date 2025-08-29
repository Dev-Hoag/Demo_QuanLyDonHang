package com.Demo_QuanLyBanHang.QuanLyBanHang.users.controllers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.dto.ApiResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.RoleRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.RoleResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponse>> createRole(@RequestBody RoleRequest role) {
        var roles = ApiResponse.<RoleResponse>builder()
                .statusCode(200)
                .message("Role created")
                .data(roleService.create(role))
                .build();
        return ResponseEntity.status(roles.getStatusCode()).body(roles);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponse>>> getRoles() {
        var roles = ApiResponse.<List<RoleResponse>>builder()
                .statusCode(200)
                .message("Roles list")
                .data(roleService.getAll())
                .build();
        return ResponseEntity.status(roles.getStatusCode()).body(roles);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteRole(@PathVariable String role) {
        roleService.deleteRole(role);
        var roles = ApiResponse.<Void>builder()
                .statusCode(200)
                .message("Role deleted")
                .build();
        return ResponseEntity.status(roles.getStatusCode()).body(roles);
    }
}
