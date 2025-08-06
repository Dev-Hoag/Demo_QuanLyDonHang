package com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.repositories;

import com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.entities.TransitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransitLogRepository extends JpaRepository<TransitLog, UUID> {
    List<TransitLog> findByHubs_HubId(UUID hubId);
}
