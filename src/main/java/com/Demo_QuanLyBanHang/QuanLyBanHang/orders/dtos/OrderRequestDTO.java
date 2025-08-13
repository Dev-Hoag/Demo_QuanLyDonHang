package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos;

import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.AreaType;

public class OrderRequestDTO {
    private String customerName;
    private String address;
    private AreaType areaType;

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public AreaType getAreaType() { return areaType; }
    public void setAreaType(AreaType areaType) { this.areaType = areaType; }
} 