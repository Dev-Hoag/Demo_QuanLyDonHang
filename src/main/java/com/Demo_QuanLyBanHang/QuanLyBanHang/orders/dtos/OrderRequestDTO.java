package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.AreaType;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {
//    private UUID senderId;
    private String senderName;
    private String senderNumber;
    private String receiverName;
    private String receiverPhoneNumber;
    private String address;
    private String oderName;
    private double weight;
    private AreaType areaType;

}