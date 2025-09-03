package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.repositories;

import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
} 