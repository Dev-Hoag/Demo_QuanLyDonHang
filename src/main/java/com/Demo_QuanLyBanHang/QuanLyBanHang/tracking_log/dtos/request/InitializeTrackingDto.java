package com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.request;

import lombok.Data;

import java.util.UUID;

@Data
public class InitializeTrackingDto {
    private UUID orderId;
}