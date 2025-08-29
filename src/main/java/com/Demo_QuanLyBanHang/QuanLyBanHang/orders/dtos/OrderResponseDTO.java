package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.OrderStatus;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.AreaType;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private UUID id;
    private String orderName;
    private String senderName;
    private String senderNumber;
    private String receiverName;
    private String receiverPhoneNumber;
    private String address;
    private OrderStatus status;
    private String oderName;
    private double weight;
    private AreaType areaType;
}