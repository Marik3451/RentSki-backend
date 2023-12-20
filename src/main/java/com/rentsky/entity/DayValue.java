package com.rentsky.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "day_values")
@Data
public class DayValue {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JoinColumn(name= "category_id",referencedColumnName = "id")
    private Category category;
    private Integer dayQuantity;
    private BigDecimal price;
}