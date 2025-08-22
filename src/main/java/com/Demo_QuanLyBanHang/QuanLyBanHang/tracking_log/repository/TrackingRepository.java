package com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.repository;

import com.Demo_QuanLyBanHang.QuanLyBanHang.tracking_log.entities.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface TrackingRepository extends JpaRepository<Tracking, UUID> {
    List<Tracking> findByOrderId(UUID orderId);
}
