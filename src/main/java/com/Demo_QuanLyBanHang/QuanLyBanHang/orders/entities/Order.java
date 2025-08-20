package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String senderName;
    private String senderNumber; // Số điện thoại người gửi
    private String receiverName;
    private String receiverPhoneNumber;
    private String address;
    private String oderName;
    private double weight;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private AreaType areaType;

    // Getters, setters, constructors
    public Order() {}

    public Order(String senderName, String senderNumber, String receiverName, String receiverPhoneNumber, String address, String orderName, double weight, OrderStatus status, AreaType areaType) {
        this.senderName = senderName;
        this.senderNumber = senderNumber;
        this.receiverName = receiverName;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.address = address;
        this.oderName = orderName;
        this.weight = weight;
        this.status = status;
        this.areaType = areaType;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public String getOderName() {
        return oderName;
    }

    public void setOderName(String oderName) {
        this.oderName = oderName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public AreaType getAreaType() { return areaType; }
    public void setAreaType(AreaType areaType) { this.areaType = areaType; }
} 