package com.rentsky.repository;

import com.rentsky.entity.Order;
import com.rentsky.entity.Ski;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SkiRepository extends JpaRepository<Ski, UUID> {

    List<Ski> findByOrder(Order order);
}
