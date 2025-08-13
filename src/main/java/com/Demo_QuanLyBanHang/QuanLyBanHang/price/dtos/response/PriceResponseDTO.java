package com.Demo_QuanLyBanHang.QuanLyBanHang.price.dtos.response;

import com.Demo_QuanLyBanHang.QuanLyBanHang.price.dtos.request.PriceRequestDTO;

// PriceResponseDTO.java
public class PriceResponseDTO {
    private double finalprice;
    private double baseprice;
    private double servicefee;
    private String status;
    private PriceRequestDTO data;




    public PriceResponseDTO(String status, PriceRequestDTO data, double baseprice, double servicefee , double finalprice) {
        this.status = status;
        this.data = data;
        this.baseprice = baseprice;
        this.servicefee = servicefee;
        this.finalprice = finalprice;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PriceRequestDTO getData() {
        return data;
    }

    public void setData(PriceRequestDTO data) {
        this.data = data;
    }

    public double getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(double finalprice) {
        this.finalprice = finalprice;
    }
    // Constructors
    public PriceResponseDTO() {}


    public double getBaseprice() {
        return baseprice;
    }

    public void setBaseprice(double baseprice) {
        this.baseprice = baseprice;
    }

    public double getServicefee() {
        return servicefee;
    }

    public void setServicefee(double servicefee) {
        this.servicefee = servicefee;
    }
}

