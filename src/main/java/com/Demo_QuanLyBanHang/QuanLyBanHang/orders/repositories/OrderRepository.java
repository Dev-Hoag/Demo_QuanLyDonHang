package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.repositories;

import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Có thể thêm các phương thức tìm kiếm tùy chỉnh nếu cần
} 