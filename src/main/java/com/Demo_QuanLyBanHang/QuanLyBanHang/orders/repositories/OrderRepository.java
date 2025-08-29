package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.repositories;

import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
} 