package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.ErrorCode;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.exceptions.AppException;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.requests.TransitLogRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.dtos.responses.TransitLogResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.Hub;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.Order;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.TransitLog;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.mappers.TransitLogMapper;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.repositories.HubRepository;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.repositories.TransitLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransitLogService {
    private final TransitLogRepository transitLogRepository;
    private final TransitLogMapper transitLogMapper;
    private final HubRepository hubRepository;

    // Tạo lịch sử đơn hàng mới
//    @PreAuthorize("hasRole('ADMIN')")
    public TransitLogResponse createTransitLog(TransitLogRequest transitLogRequest) {
//        Order order = orderRepository.findById(request.getOrderId())
//                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        Hub hub = hubRepository.findById(transitLogRequest.getHubId())
                .orElseThrow(() -> new AppException(ErrorCode.HUB_NOT_FOUND));

        if (transitLogRequest.getTransArriveAt() == null || transitLogRequest.getTransDepartureAt() == null) {
            throw new AppException(ErrorCode.INVALID_TRANSIT_ARRIVED_OR_DEPARTURED);
        }
        if (transitLogRequest.getTransDepartureAt().isBefore(transitLogRequest.getTransArriveAt())) {
            throw new AppException(ErrorCode.INVALID_DEPARTURED);
        }
        TransitLog transitLog = new TransitLog();
        transitLog.setHubs(hub);
//        transitLog.setOrder(order);
        return transitLogMapper.toTransitLogResponse(transitLogRepository.save(transitLog));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public List<TransitLogResponse> getAllTransitLogs() {
        return transitLogRepository.findAll().stream().map(transitLogMapper::toTransitLogResponse).toList();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public TransitLogResponse getTransitLogById(UUID id) {
        return transitLogMapper.toTransitLogResponse(transitLogRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_ID)));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public TransitLogResponse updateTransitLog(UUID id, TransitLogRequest transitLogRequest) {
        TransitLog transitLog = transitLogRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_ID));
        transitLogMapper.updateTransitLog(transitLog, transitLogRequest);
        return transitLogMapper.toTransitLogResponse(transitLogRepository.save(transitLog));
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTransitLog(UUID id) {
        transitLogRepository.deleteById(id);
    }

//    public List<TransitLogResponse> getTransitLogsByOrderId(UUID orderId);

//    @PreAuthorize("hasRole('ADMIN')")
    public List<TransitLogResponse> getTransitLogsByHub(UUID hubId) {
        if(hubRepository.existsById(hubId)) {
            throw new AppException(ErrorCode.INVALID_ID);
        }
        List<TransitLog> transitLog = transitLogRepository.findByHubs_HubId(hubId);
        return transitLog.stream().map(transitLogMapper::toTransitLogResponse).collect(Collectors.toList());
    }

}
