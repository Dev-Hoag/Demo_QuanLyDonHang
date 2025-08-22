package com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.controller;

import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.request.CreateTrackingDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.request.InitializeTrackingDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.request.UpdateTrackingDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.response.TrackingResponseDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.services.TrackingServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    private  TrackingServices trackingService;


    // 1. Initialize
    @PostMapping("/initialize")
    public ResponseEntity<TrackingResponseDto> initializeTracking(@RequestBody InitializeTrackingDto dto) {
        return ResponseEntity.ok(trackingService.initializeTracking(dto));
    }

    // 2. Get info
    @GetMapping("/{orderId}")
    public ResponseEntity<List<TrackingResponseDto>> getTrackingByOrder(@PathVariable UUID orderId) {
        return ResponseEntity.ok(trackingService.getTrackingByOrder(orderId));
    }

    // 3. Admin update
    @PostMapping("/update")
    public ResponseEntity<TrackingResponseDto> updateTracking(@RequestBody UpdateTrackingDto dto) {
        return ResponseEntity.ok(trackingService.updateTracking(dto));
    }
}
