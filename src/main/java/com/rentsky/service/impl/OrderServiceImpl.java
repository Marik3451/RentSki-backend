package com.rentsky.service.impl;

import com.rentsky.dto.CreateOrderDTO;
import com.rentsky.entity.Order;
import com.rentsky.entity.Ski;
import com.rentsky.entity.SkiStatus;
import com.rentsky.exception.OrderNotFoundException;
import com.rentsky.exception.SkiAlreadyTakenException;
import com.rentsky.repository.OrderRepository;
import com.rentsky.repository.SkiRepository;
import com.rentsky.service.OrderService;
import com.rentsky.service.SkiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final SkiRepository skiRepository;
    private final SkiService skiService;

    @Override
    @Transactional
    public UUID createOrder(CreateOrderDTO order){
        UUID skiId = order.getSkiId();
        SkiStatus skiStatus = skiService.getSkiStatus(skiId);
        if (SkiStatus.TAKEN.equals(skiStatus)) {
            try {
                throw new SkiAlreadyTakenException("Ski with ID " + skiId + " is already taken.");
            } catch (SkiAlreadyTakenException e) {
                throw new RuntimeException(e);
            }
        }
        Order save = orderRepository.saveAndFlush(mapDTOtoEntity(order));
        skiService.attachToOrder(skiId, save);
        skiService.updateSkiStatus(skiId, SkiStatus.TAKEN);
        return save.getId();
    }

    private Order mapDTOtoEntity(CreateOrderDTO createOrderDTO) {
        return Order.builder()
                .firstName(createOrderDTO.getFirstName())
                .lastName(createOrderDTO.getLastName())
                .phoneNumber(createOrderDTO.getPhoneNumber())
                .email(createOrderDTO.getEmail())
                .rentalDays(createOrderDTO.getRentalDays())
                .build();
    }

    @Override
    public List<Order> getOrders(Integer page, Integer pageSize) {
        return orderRepository.findAll(PageRequest.of(page, pageSize)).get().toList();
    }

    @Override
    @Transactional
    public void deleteOrder(UUID id) {
        Order orderToDelete = orderRepository.findById(id).orElse(null);
        if (orderToDelete != null) {
            try {
                List<Ski> skisToDelete = skiRepository.findByOrder(orderToDelete);

                for (Ski ski : skisToDelete) {
                    ski.setOrder(null);
                    skiService.updateSkiStatus(ski.getId(), SkiStatus.AVAILABLE);
                }

                orderRepository.deleteById(id);
            } catch (Exception e) {
                throw new RuntimeException("Failed to delete order", e);
            }
        } else {
            throw new OrderNotFoundException("Order not found with id: " + id);
        }
    }
}
