package com.adityaraj.booking_service.controller;


import com.adityaraj.booking_service.dto.InventoryRequest;
import com.adityaraj.booking_service.dto.InventoryResponse;
import com.adityaraj.booking_service.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    public InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> addInventory(@RequestBody InventoryRequest inventory) {

        InventoryResponse inventoryResponse = inventoryService.createInventory(inventory);

        return ResponseEntity.ok(inventoryResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable UUID id) {


        InventoryResponse inventoryResponse = inventoryService.getInventoryById(id);

        if(inventoryResponse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inventoryResponse);

    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<InventoryResponse> getInventoryByProductId(@PathVariable UUID productId) {

        InventoryResponse inventoryResponse = inventoryService.getInventoryByProductId(productId);

        if(inventoryResponse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inventoryResponse);

    }

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {
        List<InventoryResponse> inventoryResponses = inventoryService.getInventory();

        if(inventoryResponses == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inventoryResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateInventory(
            @PathVariable UUID id,
            @RequestBody InventoryRequest inventory) {

        InventoryResponse inventoryResponse = inventoryService.updateInventory(id, inventory);
        if(inventoryResponse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inventoryResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InventoryResponse> cancelInventory(@PathVariable UUID id) {

        InventoryResponse inventoryResponse = inventoryService.getInventoryById(id);

        if(inventoryResponse == null) {
            return ResponseEntity.notFound().build();
        }

        inventoryService.cancelInventory(id);

        return ResponseEntity.ok(inventoryResponse);

    }
}
