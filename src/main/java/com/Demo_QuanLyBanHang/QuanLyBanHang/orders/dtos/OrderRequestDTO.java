package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.AreaType;

import java.util.UUID;

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

    public String getCustomerName() { return senderName; }
    public void setCustomerName(String senderName) { this.senderName = senderName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public AreaType getAreaType() { return areaType; }
    public void setAreaType(AreaType areaType) { this.areaType = areaType; }

//    public UUID getSenderId() {
//        return senderId;
//    }
//
//    public void setSenderId(UUID senderId) {
//        this.senderId = senderId;
//    }

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
}