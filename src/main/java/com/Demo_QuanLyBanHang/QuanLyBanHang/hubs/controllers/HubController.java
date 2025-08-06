package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.controllers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.dto.ApiResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.HubRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.HubUpdateRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.responses.HubResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.services.HubService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hubs")
@RequiredArgsConstructor
public class HubController {
    private final HubService hubService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<HubResponse>> createHub(@RequestBody @Valid HubRequest hubRequest) {
        HubResponse result = hubService.createHub(hubRequest);
        var response = ApiResponse.<HubResponse>builder()
                .statusCode(201)
                .message("Tạo trạm thành công")
                .data(result)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<HubResponse>>> getHubs() {
        List<HubResponse> hubs = hubService.getAllHubs();

        var response = ApiResponse.<List<HubResponse>>builder()
                .statusCode(200)
                .message("Lấy danh sách các trạm thành công")
                .data(hubs)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{hubId}")
    public ResponseEntity<ApiResponse<HubResponse>> getHubById(@PathVariable UUID hubId) {
        HubResponse hubResponse = hubService.getHubById(hubId);
        var response = ApiResponse.<HubResponse>builder()
                .statusCode(200)
                .message("Lấy trạm theo ID thành công")
                .data(hubResponse)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{hubId}")
    public ResponseEntity<ApiResponse<HubResponse>> updateHub(@PathVariable UUID hubId, @RequestBody @Valid HubUpdateRequest hubRequest) {
        HubResponse hubResponse = hubService.updateHub(hubId, hubRequest);

        var response = ApiResponse.<HubResponse>builder()
                .statusCode(200)
                .message("Cập trạm thành công")
                .data(hubResponse)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{hubId}")
    public ResponseEntity<ApiResponse<String>> deleteHub(@PathVariable UUID hubId) {
        hubService.deleteHub(hubId);
        var response = ApiResponse.<String>builder()
                .statusCode(200)
                .data("Xóa trạm thành công")
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{hubName}")
    public ResponseEntity<ApiResponse<HubResponse>> getHubByName(@PathVariable String hubName) {
        HubResponse hubResponse = hubService.getHubByName(hubName);
        var response = ApiResponse.<HubResponse>builder()
                .statusCode(200)
                .message("Lấy trạm theo tên thành công")
                .data(hubResponse)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{hubAddress}")
    public ResponseEntity<ApiResponse<HubResponse>> getHubByAddress(@PathVariable String hubAddress) {
        HubResponse hubResponse = hubService.getHubByAddress(hubAddress);
        var response = ApiResponse.<HubResponse>builder()
                .statusCode(200)
                .message("Lấy trạm theo địa chỉ thành công")
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<HubResponse>>> searchHub(@RequestParam(required = false) String name,
                                                                    @RequestParam(required = false) String address) {
        List<HubResponse> hubs = hubService.searchHubs(name, address);
        var response = ApiResponse.<List<HubResponse>>builder()
                .statusCode(200)
                .message("Tìm kiếm trạm thành công")
                .data(hubs)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
