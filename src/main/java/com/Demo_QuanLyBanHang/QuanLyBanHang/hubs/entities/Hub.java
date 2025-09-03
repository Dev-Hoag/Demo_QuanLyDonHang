package com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities;

import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.enums.HubStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Hub {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private UUID hubId;

    @Column(nullable = false, unique = false, length = 255)
    private String hubName;

    @Column(nullable = false, unique = true, length = 255)
    private String hubAddress;

    @Column(nullable = false, length = 50)
    private String hubRegion;

    @Column(nullable = false, length = 255)
    private long orderCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private HubStatus hubStatus;

    public void increaseOrderCount() {
        this.orderCount++;
    }
}
