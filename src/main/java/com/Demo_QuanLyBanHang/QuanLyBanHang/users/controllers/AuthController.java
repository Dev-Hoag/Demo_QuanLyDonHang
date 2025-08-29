package com.Demo_QuanLyBanHang.QuanLyBanHang.users.controllers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.dto.ApiResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.SignIn;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.SignOut;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.request.SignUp;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.dtos.response.AuthResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.services.AuthService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<AuthResponse>> signIn(@RequestBody @Valid SignIn request) {
        AuthResponse authResponse = authService.signIn(request);
        var response = ApiResponse.<AuthResponse>builder()
                .statusCode(200)
                .message("OK")
                .data(authResponse)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<AuthResponse>> signUp(@RequestBody @Valid SignUp request) {
        AuthResponse authResponse = authService.signUp(request);
        var response = ApiResponse.<AuthResponse>builder()
                .statusCode(200)
                .message("Đăng ký thành công")
                .data(authResponse)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<ApiResponse<String>> signOut(@RequestBody SignOut request) throws ParseException, JOSEException {
        authService.signOut(request);
        var  response = ApiResponse.<String>builder()
                .statusCode(200)
                .message("Đăng xuất thành công")
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
