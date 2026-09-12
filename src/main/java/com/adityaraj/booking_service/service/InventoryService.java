package com.adityaraj.booking_service.service;

import com.adityaraj.booking_service.dto.InventoryRequest;
import com.adityaraj.booking_service.dto.InventoryResponse;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    InventoryResponse createInventory(InventoryRequest inventory);

    InventoryResponse getInventoryById(UUID id);

    InventoryResponse getInventoryByProductId(UUID productId);

    List<InventoryResponse> getInventory();

    InventoryResponse updateInventory(
            UUID id,
            InventoryRequest inventory);

    void cancelInventory(UUID id);
}
