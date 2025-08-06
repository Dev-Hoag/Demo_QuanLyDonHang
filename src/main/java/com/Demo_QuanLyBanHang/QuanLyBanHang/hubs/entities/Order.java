package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Order {
    @Id
    private String orderId;

    @Column(unique=false, nullable=true, length=255)
    private String orderName;
}
