package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.controllers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.dto.ApiResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.TransitLogRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.TransitLogUpdateRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.responses.TransitLogResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.services.TransitLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transLog")
@RequiredArgsConstructor
public class TransitLogController {
    private final TransitLogService transLogService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<TransitLogResponse>> createTransitLog(@RequestBody @Valid TransitLogRequest transLogRequest) {
        TransitLogResponse transLogResponse = transLogService.createTransitLog(transLogRequest);
        var response = ApiResponse.<TransitLogResponse>builder()
                .statusCode(200)
                .message("Tạo nhật ký trung chuyển thành công")
                .data(transLogResponse)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<TransitLogResponse>>> getListTransitLogs() {
        List<TransitLogResponse> transLog = transLogService.getAllTransitLogs();
        var response = ApiResponse.<List<TransitLogResponse>>builder()
                .statusCode(200)
                .message("Lấy danh sách trung chuyển thành công")
                .data(transLog)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{transId}")
    public ResponseEntity<ApiResponse<TransitLogResponse>> getTransitLog(@PathVariable UUID transId) {
        TransitLogResponse transResponse = transLogService.getTransitLogById(transId);
        var response = ApiResponse.<TransitLogResponse>builder()
                .statusCode(200)
                .message("Lấy lịch sử trung chuyển theo ID thành công")
                .data(transResponse)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/{transId}")
    public ResponseEntity<ApiResponse<TransitLogResponse>> updateTransitLog(@PathVariable UUID transId, @RequestBody @Valid TransitLogUpdateRequest transUpdate) {
        TransitLogResponse transUpdateResponse = transLogService.updateTransitLog(transId, transUpdate);
        var response = ApiResponse.<TransitLogResponse>builder()
                .statusCode(200)
                .message("Cập nhật lịch sử trung chuyển thành công")
                .data(transUpdateResponse)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/{transId}")
    public ResponseEntity<ApiResponse<String>> deleteTransitLog(@PathVariable UUID transId) {
        transLogService.deleteTransitLog(transId);
        var response = ApiResponse.<String>builder()
                .statusCode(200)
                .data("Xóa lịch sử trung chuyển thành công")
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/hubs/{hubId}/transit-logs")
    public ResponseEntity<ApiResponse<List<TransitLogResponse>>> searchTransLog(@PathVariable UUID hubId) {
        List<TransitLogResponse> listTransLog = transLogService.getTransitLogsByHub(hubId);
        var response = ApiResponse.<List<TransitLogResponse>>builder()
                .statusCode(200)
                .message("Lấy lịch sử trung chuyển theo kho hàng thành công")
                .data(listTransLog)
                .build();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
