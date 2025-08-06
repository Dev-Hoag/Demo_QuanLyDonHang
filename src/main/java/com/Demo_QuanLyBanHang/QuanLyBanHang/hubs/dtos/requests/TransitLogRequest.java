package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests;

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
