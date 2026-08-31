package com.adityaraj.booking_service.dto;

import com.adityaraj.booking_service.entity.Product;
import com.adityaraj.booking_service.entity.enums.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingResponse {

    private UUID id;
    private UUID userId;
    private Product product;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private Status paymentStatus;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
