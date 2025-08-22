package com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateTrackingDto {
    private UUID orderId;
    private String status;
    private UUID hubId;   // optional
    private String note;  // optional
    private String updatedBy;
}
