package com.rentsky.controller;

import com.rentsky.dto.CreateOrderDTO;
import com.rentsky.entity.Order;
import com.rentsky.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderDTO order) {
        try {
            orderService.createOrder(order);
            return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create order: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public List<Order> getOrders(@RequestParam(required = false, defaultValue = "0")
                             Integer page,
                             @RequestParam(required = false, defaultValue = "50")
                             Integer pageSize) {
        return orderService.getOrders(page, pageSize);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable UUID id)
    {
        orderService.deleteOrder(id);
    }

}
