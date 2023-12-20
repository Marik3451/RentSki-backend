package com.rentsky.repository;

import com.rentsky.entity.DayValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DayValueRepository extends JpaRepository<DayValue, UUID> {
    List<DayValue> getAllByCategory_Id(UUID categoryId);
}
