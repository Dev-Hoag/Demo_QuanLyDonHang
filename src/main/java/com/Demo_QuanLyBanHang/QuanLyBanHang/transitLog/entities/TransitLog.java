package com.Demo_QuanLyBanHang.QuanLyBanHang.transitLog.entities;

import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.Hub;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.Order;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransitLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false, length = 50)
    private UUID transId;

    @Column(nullable = false, length = 50)
    private Instant transArriveAt; // Thời điểm mà đơn hàng được chuyển đến một trạm giao nhận (hub) cụ thể. Ví dụ: Đơn hàng đến trạm Hub A lúc 10:00 sáng, thì arrived_at = 2025-08-07 10:00:00.

    @Column(nullable = false, length = 50)
    private Instant transDepartureAt; // Thời điểm mà đơn hàng rời khỏi trạm giao nhận đó để tiếp tục hành trình đến hub kế tiếp hoặc đến tay. Ví dụ: Đơn hàng rời trạm Hub A lúc 13:30 chiều, thì departed_at = 2025-08-07 13:30:00

    @Column(nullable = false, length = 50)
    private String handTo; // Thông tin về người hoặc bộ phận tiếp nhận đơn hàng khi nó rời khỏi kho. Ví dụ: Hàng rời Hub A, được giao cho shipper Nguyễn Văn B: handed_to = "Nguyễn Văn B"

    @ManyToOne(fetch = FetchType.LAZY)
    private Order orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hub hubs;
}
