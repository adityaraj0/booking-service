package com.adityaraj.booking_service.dto;

import com.adityaraj.booking_service.entity.Product;
import com.adityaraj.booking_service.entity.ProductType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private Product product;

//    @NotNull
//    private ProductType productType;

    private LocalDateTime startTime;
    private LocalDateTime endTime;


//    private UUID paymentId;
}
