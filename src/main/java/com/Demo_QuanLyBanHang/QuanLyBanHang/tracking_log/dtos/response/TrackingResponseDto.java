package com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.response;

import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.entities.Tracking;

import java.time.LocalDateTime;
import java.util.UUID;

public class TrackingResponseDto {
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getHubId() {
        return hubId;
    }

    public void setHubId(UUID hubId) {
        this.hubId = hubId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    private UUID id;
    private UUID orderId;
    private UUID hubId;
    private String status;
    private String note;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TrackingResponseDto fromEntity(Tracking tracking) {
        TrackingResponseDto dto = new TrackingResponseDto();
        dto.setId(tracking.getId());
        dto.setOrderId(tracking.getOrderId());
        dto.setHubId(tracking.getHubId());
        dto.setStatus(tracking.getStatus());
        dto.setNote(tracking.getNote());
        dto.setUpdatedBy(tracking.getUpdatedBy());
        dto.setCreatedAt(tracking.getCreatedAt());
        dto.setUpdatedAt(tracking.getUpdatedAt());
        return dto;
    }


}

