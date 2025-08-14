package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HubRequest {
    private String hubName;
    private String hubAddress;
    private String hubRegion;
}
