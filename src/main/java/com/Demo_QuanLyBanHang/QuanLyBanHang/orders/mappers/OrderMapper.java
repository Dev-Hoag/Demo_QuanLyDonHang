package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.mappers;

import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos.OrderRequestDTO;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos.OrderResponseDTO;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO toOrderResponseDTO(Order order);

    void updateOrder(@MappingTarget Order order, OrderRequestDTO orderRequestDTO);
}
