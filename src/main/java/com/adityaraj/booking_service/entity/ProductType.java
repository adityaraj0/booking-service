package com.adityaraj.booking_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product-type", schema = "booking-service")
public class ProductType {

    @Id
    private Long id;

    private String name;
}
