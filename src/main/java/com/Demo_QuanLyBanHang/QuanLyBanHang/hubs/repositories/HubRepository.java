package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.repositories;

import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.Hub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HubRepository extends JpaRepository<Hub, UUID> {
    boolean existsByHubName(String hubName);
    boolean existsByHubAddress(String hubAddress);
    Optional<Hub> findByHubName(String hubName);
    Optional<Hub> findByHubAddress(String hubAddress);
    List<Hub> findByHubNameContainingIgnoreCaseAndHubAddressContainingIgnoreCase(String hubName, String hubAddress);
}
