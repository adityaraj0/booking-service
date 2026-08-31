package com.adityaraj.booking_service.dto;

import com.adityaraj.booking_service.entity.ProductType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductResponse {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal amount;
    private ProductType productType;
    private Boolean active;

}
