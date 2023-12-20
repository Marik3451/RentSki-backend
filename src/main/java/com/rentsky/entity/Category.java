package com.rentsky.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "categories")
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue
    private UUID id;
    private String category;

}