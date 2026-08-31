package com.adityaraj.booking_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingRequest {

    @NotNull
    private UUID userId;

    @NotNull
    private UUID productId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;


//    private UUID paymentId;
}
