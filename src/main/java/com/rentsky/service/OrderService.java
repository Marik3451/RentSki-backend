package com.rentsky.service;

import com.rentsky.dto.CreateOrderDTO;
import com.rentsky.entity.Order;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface OrderService {
    UUID createOrder(@Valid CreateOrderDTO order);
    List<Order> getOrders(Integer page, Integer pageSize);
    void deleteOrder(UUID id);
}
