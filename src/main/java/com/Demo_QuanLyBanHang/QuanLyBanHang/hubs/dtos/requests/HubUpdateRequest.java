package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests;

import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.enums.HubStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HubUpdateRequest {
    private String hubName;
    private String hubAddress;
    private String hubRegion;
    private HubStatus hubStatus;
}
