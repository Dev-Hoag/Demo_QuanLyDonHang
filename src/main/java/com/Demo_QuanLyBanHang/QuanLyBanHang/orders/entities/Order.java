package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities;

import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.Hub;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String receiverName;
    private String receiverPhoneNumber;
    private String address;
    private String oderName;
    private double weight;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private AreaType areaType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hub_id")
    private Hub hub;

}
