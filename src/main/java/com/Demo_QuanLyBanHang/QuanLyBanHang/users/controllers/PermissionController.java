package com.Demo_QuanLyBanHang.QuanLyBanHang.users.controllers;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.dto.ApiResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.PermissionRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.PermissionResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @PostMapping
    public ResponseEntity<ApiResponse<PermissionResponse>> createPermission(PermissionRequest request) {
        var permission = permissionService.createPermission(request);
        var apiResponse = ApiResponse.<PermissionResponse>builder()
                .statusCode(200)
                .message("Permission created")
                .data(permission)
                .build();
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PermissionResponse>>> getPermission() {
        var permission = ApiResponse.<List<PermissionResponse>>builder()
                .statusCode(200)
                .message("Permission list")
                .data(permissionService.getAll())
                .build();
        return ResponseEntity.status(permission.getStatusCode()).body(permission);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deletePermission(@PathVariable String permission) {
        permissionService.deletePermission(permission);
        var permissions = ApiResponse.<Void>builder()
                .statusCode(200)
                .message("Deleted permission")
                .build();
        return ResponseEntity.status(permissions.getStatusCode()).body(permissions);
    }
}
