package com.Demo_QuanLyBanHang.QuanLyBanHang.price.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long priceId;

    @Column(name = "route_code", nullable = false)
    private String routeCode;

    @Column(name = "route_name", nullable = false)
    private String routeName;

    @Column(name = "weight", nullable = false)
    private BigDecimal weight;

    @Column(name = "baseprice", nullable = false)
    private BigDecimal basePrice;

    // Getters and setters
    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }
}

