package com.Demo_QuanLyBanHang.QuanLyBanHang.price.repositories;

import com.Demo_QuanLyBanHang.QuanLyBanHang.price.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    // Query only the coefficient by serviceType
    @Query("SELECT s.coefficient FROM ServiceEntity s WHERE s.serviceType = :serviceType")
    Optional<BigDecimal> findCoefficientByServiceType(
            @Param("serviceType") String serviceType
    );
}
