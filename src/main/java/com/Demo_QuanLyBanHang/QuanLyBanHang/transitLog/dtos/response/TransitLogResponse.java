package com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.response;

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
// Đây là class lịch sử vận chuyển
public class TransitLogResponse {
    private UUID transitLogId;
    private Instant transArriveAt;
    private Instant transDepartureAt;
    private String handTo;
    private String orderName;
    private String hubName;
}
