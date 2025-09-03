package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.responses;

import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.enums.HubStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HubResponse {
    private UUID hubId;
    private String hubName;
    private String hubAddress;
    private String hubRegion;
    private long orderCount;
    private HubStatus hubStatus;
}
