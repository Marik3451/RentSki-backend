package com.rentsky.service.impl;

import com.rentsky.entity.Ski;
import com.rentsky.entity.SkiStatus;
import com.rentsky.exception.SkiNotFoundException;
import com.rentsky.repository.SkiRepository;
import com.rentsky.service.SkiService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SkiServiceImpl implements SkiService {
    private final SkiRepository skiRepository;

    @Override
    public List<Ski> getSkis(Integer page, Integer pageSize) {
        return skiRepository.findAll(PageRequest.of(page, pageSize)).get().toList();
    }

    @Override
    @Transactional
    public void attachToOrder(UUID skiId, UUID orderId) {
       skiRepository.setOrderId(skiId,orderId);
    }
    @Override
    public void updateSkiStatus(UUID skiId, SkiStatus newStatus) {
        Optional<Ski> optionalSki = skiRepository.findById(skiId);
        if (optionalSki.isPresent()) {
            Ski ski = optionalSki.get();
            ski.setSkiStatus(newStatus);
            skiRepository.save(ski);
        } else {
            // обробка випадку, коли лижа з вказаним ID не знайдена
            throw new IllegalArgumentException("Лижа не знайдена з ID: " + skiId);
        }
    }
    @Override
    public SkiStatus getSkiStatus(UUID skiId) {
        Ski ski = skiRepository.findById(skiId).orElse(null);

        if (ski != null) {
            return ski.getSkiStatus();
        } else {
            throw new SkiNotFoundException("Ski not found with ID: " + skiId);
        }
    }

}
