package com.Demo_QuanLyBanHang.QuanLyBanHang.orders.services;

import com.Demo_QuanLyBanHang.QuanLyBanHang.common.enums.ErrorCode;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.exceptions.AppException;
import com.Demo_QuanLyBanHang.QuanLyBanHang.common.utils.AuthUtil;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.entities.Hub;
import com.Demo_QuanLyBanHang.QuanLyBanHang.hubs.services.HubService;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.Order;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.entities.OrderStatus;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.mappers.OrderMapper;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.repositories.OrderRepository;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos.OrderRequestDTO;
import com.Demo_QuanLyBanHang.QuanLyBanHang.orders.dtos.OrderResponseDTO;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.entities.User;
import com.Demo_QuanLyBanHang.QuanLyBanHang.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;
    private final HubService hubService;

    public OrderResponseDTO createOrder(OrderRequestDTO dto) {

        UUID userId = AuthUtil.getUserIdFromContext();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

       // Hub hub = hubService.findHubByRegionOrAddress(dto.getAddress());

        Order order = orderMapper.toOrder(dto);
        order.setUser(user);
        order.setSenderName(dto.getSenderName());
        order.setStatus(OrderStatus.CREATED);
       // order.setHub(hub);

        //hub.increaseOrderCount();

        Order result = orderRepository.save(order);
        return orderMapper.toOrderResponseDTO(orderRepository.save(result));
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> orderMapper.toOrderResponseDTO(order)).toList();
    }

    public Optional<OrderResponseDTO> getOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(order ->  orderMapper.toOrderResponseDTO(order));
    }

    public OrderResponseDTO updateOrder(UUID id, OrderRequestDTO dto) {
        Order order =  orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        orderMapper.updateOrder(order, dto);

        return orderMapper.toOrderResponseDTO(orderRepository.save(order));
    }

    public void deleteOrder(UUID id) {
        orderRepository.deleteById(id);
    }

    public OrderResponseDTO updateStatus(UUID id, OrderStatus status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        order.setStatus(status);

        return  orderMapper.toOrderResponseDTO(orderRepository.save(order));
    }
} 