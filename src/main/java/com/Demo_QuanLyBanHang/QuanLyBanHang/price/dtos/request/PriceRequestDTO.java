package com.Demo_QuanLyBanHang.QuanLyBanHang.price.dtos.request;
import java.math.BigDecimal;
public class PriceRequestDTO {


    private String routeCode;
    private String sendLocation;
    private String receiveLocation;
    private BigDecimal weight;
    private String serviceType;

    public PriceRequestDTO() {}

    public PriceRequestDTO(String sendLocation, String receiveLocation,String routeCode, BigDecimal weight, String serviceType) {
        this.sendLocation = sendLocation;
        this.receiveLocation = receiveLocation;
        this.routeCode = routeCode;
        this.weight = weight;
        this.serviceType = serviceType;
    }

    public String getSendLocation() {
        return sendLocation;
    }
    public void setSendLocation(String sendLocation) {
        this.sendLocation = sendLocation;
    }

    public String getReceiveLocation() {
        return receiveLocation;
    }
    public void setReceiveLocation(String receiveLocation) {
        this.receiveLocation = receiveLocation;
    }

    public BigDecimal getWeight() {
        return weight;
    }
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getServiceType() {
        return serviceType;
    }
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }
}