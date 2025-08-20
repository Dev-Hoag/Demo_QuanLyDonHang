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
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderResponseDTO createOrder(OrderRequestDTO dto) {
        Order order = new Order();
        order.setOderName(dto.getOderName());
        order.setSenderName(dto.getSenderName());
        order.setReceiverName(dto.getReceiverName());
        order.setSenderNumber(dto.getSenderNumber());
        order.setReceiverPhoneNumber(dto.getReceiverPhoneNumber());
        order.setWeight(dto.getWeight());
        order.setAddress(dto.getAddress());
        order.setAreaType(dto.getAreaType());
        order.setStatus(OrderStatus.CREATED);
        return toResponseDTO(orderRepository.save(order));
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public Optional<OrderResponseDTO> getOrderById(UUID id) {
        return orderRepository.findById(id).map(this::toResponseDTO);
    }

    public OrderResponseDTO updateOrder(UUID id, OrderRequestDTO dto) {
        return orderRepository.findById(id).map(order -> {
            order.setReceiverPhoneNumber( dto.getReceiverPhoneNumber());
            order.setReceiverName(dto.getReceiverName());
            order.setAddress(dto.getAddress());
            return toResponseDTO(orderRepository.save(order));
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

    public OrderResponseDTO updateStatus(UUID id, OrderStatus status) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(status);
            return toResponseDTO(orderRepository.save(order));
        }).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    private OrderResponseDTO toResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setOderName(order.getOderName());
        dto.setSenderName(order.getSenderName());
        dto.setReceiverName(order.getReceiverName());
        dto.setSenderNumber(order.getSenderNumber());
        dto.setReceiverPhoneNumber(order.getReceiverPhoneNumber());
        dto.setWeight(order.getWeight());
        dto.setAddress(order.getAddress());
        dto.setStatus(order.getStatus());
        dto.setAreaType(order.getAreaType());
        return dto;
    }
} 