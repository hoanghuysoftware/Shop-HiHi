package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.OrderDTO;
import com.personal.beshophihi.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    Order createOrder(OrderDTO orderDTO);
    Order updateOrder(Long id, Long isStatus);
}
