package com.Demo_QuanLyBanHang.QuanLyBanHang.price.repositories;

import com.Demo_QuanLyBanHang.QuanLyBanHang.price.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {


    @Query("SELECT p.basePrice FROM Price p WHERE p.routeCode = :routeCode AND p.weight >= :weight ORDER BY p.weight ASC LIMIT 1")
    Optional<Double> findBasePriceByRouteCodeAndWeight(@Param("routeCode") String routeCode, @Param("weight") BigDecimal weight);
}
