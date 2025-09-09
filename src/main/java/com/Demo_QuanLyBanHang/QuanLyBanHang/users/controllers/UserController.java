package com.Demo_QuanLyBanHang.QuanLyBanHang.users.controllers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.dto.ApiResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.utils.AuthUtil;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.UserUpdateRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.UserResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get-user-profile")
    public ResponseEntity<ApiResponse<UserResponse>> getUserProfile() {

        UUID userid = AuthUtil.getUserIdFromContext();

        UserResponse user = userService.getUserProfile(userid);
        user.setUserId(userid);

        var response = ApiResponse.<UserResponse>builder()
                .statusCode(200)
                .message("Success")
                .data(user)
                .build();

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers() {
        var response = ApiResponse.<List<UserResponse>>builder()
                .statusCode(200)
                .message("Success")
                .data(userService.getAllUsers())
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable UUID userId) {
        var response = ApiResponse.<UserResponse>builder()
                .statusCode(200)
                .message("Success")
                .data(userService.getUserProfile(userId))
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> updateInfo(@PathVariable UUID userId, @RequestBody UserUpdateRequest userUpdateRequest) {
        UserResponse user = userService.updateUser(userId, userUpdateRequest);
        var response = ApiResponse.<UserResponse>builder()
                .statusCode(200)
                .message("Cập nhật người dùng thành công")
                .data(user)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        var response = ApiResponse.<String>builder()
                .statusCode(200)
                .message("Deleted Success")
                .data("User has been deleted")
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
