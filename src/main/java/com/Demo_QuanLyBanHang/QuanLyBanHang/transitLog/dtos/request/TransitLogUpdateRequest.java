package com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransitLogUpdateRequest {
    private Instant transArriveAt;
    private Instant transDepartureAt;
    private String handTo;
}
