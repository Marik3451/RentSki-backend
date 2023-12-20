package com.rentsky.repository;

import com.rentsky.entity.Order;
import com.rentsky.entity.Ski;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SkiRepository extends JpaRepository<Ski, UUID> {
    @Query("update Ski s set s.order.id=:orderId where s.id=:skiId")
    @Modifying
    void setOrderId(@Param("skiId") UUID skiId,@Param("orderId") UUID orderId);

    List<Ski> findByOrder(Order order);
}
