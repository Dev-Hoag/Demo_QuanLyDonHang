package com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.request;

import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateTrackingDto {

    @NotNull(message = "Order ID is required")
    private UUID orderId;

    private UUID hubId; // optional

    @NotNull(message = "Status is required")
    @Size(max = 50, message = "Status cannot exceed 50 characters")
    private String status;

    private String note; // optional

    private String updatedBy; // optional, can be system/admin/user

    // Getters and Setters
    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(@NotNull UUID orderId) {
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
}
