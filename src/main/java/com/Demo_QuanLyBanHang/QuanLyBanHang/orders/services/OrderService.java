package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.Order;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.OrderStatus;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.repositories.OrderRepository;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos.OrderRequestDTO;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        Order order = new Order();
        order.setCustomerName(dto.getCustomerName());
        order.setAddress(dto.getAddress());
        order.setAreaType(dto.getAreaType());
        order.setStatus(OrderStatus.CREATED);
        return toResponseDTO(orderRepository.save(order));
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public Optional<OrderResponseDTO> getOrderById(Long id) {
        return orderRepository.findById(id).map(this::toResponseDTO);
    }

    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO dto) {
        return orderRepository.findById(id).map(order -> {
            order.setCustomerName(dto.getCustomerName());
            order.setAddress(dto.getAddress());
            order.setAreaType(dto.getAreaType());
            return toResponseDTO(orderRepository.save(order));
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderResponseDTO updateStatus(Long id, OrderStatus status) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(status);
            return toResponseDTO(orderRepository.save(order));
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    private OrderResponseDTO toResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setAddress(order.getAddress());
        dto.setStatus(order.getStatus());
        dto.setAreaType(order.getAreaType());
        return dto;
    }
} 