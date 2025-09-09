package com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.ErrorCode;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.exceptions.AppException;
import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.request.TransitLogRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.request.TransitLogUpdateRequest;
import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.response.TransitLogDashboardResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.dtos.response.TransitLogResponse;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.Hub;
import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.entities.TransitLog;
import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.mappers.TransitLogMapper;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.repositories.HubRepository;
import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.repositories.TransitLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
        transitLogMapper.toTransitLog(transitLogRequest);
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
    public TransitLogResponse updateTransitLog(UUID id, TransitLogUpdateRequest transitLogUpdateRequest) {
        TransitLog transitLog = transitLogRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.INVALID_ID));
        transitLogMapper.updateTransitLog(transitLog, transitLogUpdateRequest);
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

    public double getAverageTransitDurationHours() {
        List<TransitLog> logs = transitLogRepository.findAll();

        if(logs.isEmpty()) {
            return 0;
        }

        double totalHours = logs.stream()
                .mapToDouble(log -> Duration.between(log.getTransArriveAt(), log.getTransDepartureAt()).toMinutes() / 60.0)
                .sum();

        return totalHours / logs.size();
    }

    public double getOnTimeRate(){
        List<TransitLog> logs =  transitLogRepository.findAll();

        if(logs.isEmpty()) {
            return 0;
        }

        long onTimeCount = logs.stream()
                .filter(log -> Duration.between(log.getTransArriveAt(), log.getTransDepartureAt()).toHours() <= 24)
                .count();

        return (double) onTimeCount / logs.size() * 100;
    }

    public Map<String, Long> getTopHandTo(int limit){
        return transitLogRepository.findAll().stream()
                .filter(log -> log.getHandTo() != null)
                .collect(Collectors.groupingBy(TransitLog::getHandTo, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limit)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public Map<String, Long> getDistributionByHub() {
        return transitLogRepository.findAll().stream()
                .filter(log -> log.getHubs() != null)
                .collect(Collectors.groupingBy(
                        log -> log.getHubs().getHubName(), // assuming `Hub` has a `getName()` method
                        Collectors.counting()
                ));
    }

    public TransitLogDashboardResponse getDashboardStatistics() {
        double avgHours = getAverageTransitDurationHours();
        double onTime = getOnTimeRate();
        Map<String, Long> topHand = getTopHandTo(10);
        Map<String, Long> byHub = getDistributionByHub();

        return new TransitLogDashboardResponse(avgHours, onTime, topHand, byHub);
    }
}
