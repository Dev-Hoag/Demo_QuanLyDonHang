package com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.services;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.Order;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.repositories.OrderRepository;
import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.request.CreateTrackingDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.request.InitializeTrackingDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.request.UpdateTrackingDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.dtos.response.TrackingResponseDto;
import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.entities.Tracking;
import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.repository.TrackingRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TrackingServices {

    private  TrackingRepository trackingRepository;

    // 1. Initialize tracking
    public TrackingResponseDto initializeTracking(InitializeTrackingDto dto) {
        Tracking tracking = new Tracking();
        tracking.setOrderId(dto.getOrderId()); // just set ID, no query to Order
        tracking.setStatus("Initialized");
        tracking.setUpdatedBy("System");

        return TrackingResponseDto.fromEntity(trackingRepository.save(tracking));
    }

    // 2. Get tracking timeline
    public List<TrackingResponseDto> getTrackingByOrder(UUID orderId) {
        return trackingRepository.findByOrderId(orderId)
                .stream()
                .map(TrackingResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 3. Update tracking (status + hubId)
    public TrackingResponseDto updateTracking(UpdateTrackingDto dto) {
        Tracking tracking = new Tracking();
        tracking.setOrderId(dto.getOrderId());
        tracking.setStatus(dto.getStatus());
        tracking.setHubId(dto.getHubId());  // directly set hubId
        tracking.setNote(dto.getNote());
        tracking.setUpdatedBy(dto.getUpdatedBy());

        return TrackingResponseDto.fromEntity(trackingRepository.save(tracking));
    }
}
