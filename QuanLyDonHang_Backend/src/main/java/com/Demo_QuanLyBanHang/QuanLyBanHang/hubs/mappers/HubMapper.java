package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.mappers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.HubRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.HubUpdateRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.responses.HubResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.Hub;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HubMapper {
    Hub toHub(HubRequest hubRequest);

    HubResponse toHubResponse(Hub hub);

    void updateHub(@MappingTarget Hub hub, HubUpdateRequest hubUpdateRequest);
}
