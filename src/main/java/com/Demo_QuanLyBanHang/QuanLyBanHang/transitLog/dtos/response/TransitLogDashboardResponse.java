package com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransitLogDashboardResponse {
    private double averageTransitHours;
    private double onTimeRate;
    private Map<String, Long> topHandTo;
    private Map<String, Long> distributionByHub;
}
