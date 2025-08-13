package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities;

public enum AreaType {
    NOI_THANH("NOI_THANH"),
    NGOAI_THANH("NGOAI_THANH");

    private final String value;

    AreaType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public static AreaType fromString(String value) {
        for (AreaType type : AreaType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown AreaType: " + value);
    }
} 