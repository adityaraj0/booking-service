package com.adityaraj.booking_service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductTypeResponse {
    private UUID id;
    private String name;
}
