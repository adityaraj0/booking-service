package com.adityaraj.booking_service.dto;
import com.adityaraj.booking_service.entity.Product;
import lombok.Data;

import java.util.UUID;

@Data
public class InventoryResponse {
    private UUID id;
    private Product product;
    private Integer totalQuantity;
    private Integer availableQuantity;
    private Boolean active;
}
