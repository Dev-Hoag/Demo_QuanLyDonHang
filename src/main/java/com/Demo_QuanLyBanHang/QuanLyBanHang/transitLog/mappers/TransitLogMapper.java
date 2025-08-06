package com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.mappers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.request.TransitLogRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.request.TransitLogUpdateRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.response.TransitLogResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.entities.TransitLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransitLogMapper {
//    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "hubs", ignore = true)
    TransitLog toTransitLog(TransitLogRequest transitLogRequest);

    TransitLogResponse toTransitLogResponse(TransitLog transitLog);

//    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "hubs", ignore = true)
    void updateTransitLog(@MappingTarget TransitLog transitLog, TransitLogUpdateRequest transitLogUpdateRequest);

}
