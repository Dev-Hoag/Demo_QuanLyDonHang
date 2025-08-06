package com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransitLogRequest {
    private Instant transArriveAt;
    private Instant transDepartureAt;
    private String handTo;
    private UUID orderId;
    private UUID hubId;
}
