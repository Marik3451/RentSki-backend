package com.rentsky.service;

import com.rentsky.entity.Ski;
import com.rentsky.entity.SkiStatus;

import java.util.List;
import java.util.UUID;

public interface SkiService {
    List<Ski> getSkis(Integer page, Integer pageSize);
    void attachToOrder(UUID skiId, UUID orderId);
    void updateSkiStatus(UUID skiId, SkiStatus newStatus);
    SkiStatus getSkiStatus(UUID skiId);
}
