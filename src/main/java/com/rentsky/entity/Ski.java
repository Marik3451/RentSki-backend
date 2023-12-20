package com.rentsky.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Table(name = "skis")
@Entity
@Data
public class Ski {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(referencedColumnName = "id",name = "category_id"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "id",name = "ski_id"),
            name = "categories_skies"
    )
    private List<Category> category;
    private String descriptions;
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;
    @Enumerated(EnumType.STRING)
    private SkiStatus skiStatus;
}
