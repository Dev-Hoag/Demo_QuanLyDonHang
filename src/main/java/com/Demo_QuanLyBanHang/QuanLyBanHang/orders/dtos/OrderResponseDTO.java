package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.OrderStatus;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.AreaType;

import java.util.UUID;

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

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public AreaType getAreaType() { return areaType; }
    public void setAreaType(AreaType areaType) { this.areaType = areaType; }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
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

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getSenderNumber() {
        return senderNumber;
    }

    public void setSenderNumber(String senderNumber) {
        this.senderNumber = senderNumber;
    }
}