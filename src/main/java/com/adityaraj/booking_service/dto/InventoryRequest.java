package com.adityaraj.booking_service.dto;

import com.adityaraj.booking_service.entity.Product;


import java.util.UUID;

public class InventoryRequest {
    private UUID id;
    private Product product;
    private Integer totalQuantity;
    private Integer availableQuantity;
    private Boolean active;
}
