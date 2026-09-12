package com.adityaraj.booking_service.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NonNull;


import java.util.UUID;

@Data
public class InventoryRequest {

    @NonNull
    private UUID productId;

    @NonNull
    @Min(0)
    private Integer totalQuantity;

    @NonNull
    @Min(0)
    private Integer availableQuantity;

    @NonNull
    private Boolean active;
}
