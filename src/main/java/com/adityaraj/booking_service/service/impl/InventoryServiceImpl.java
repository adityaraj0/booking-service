package com.adityaraj.booking_service.service.impl;

import com.adityaraj.booking_service.dto.InventoryRequest;
import com.adityaraj.booking_service.dto.InventoryResponse;
import com.adityaraj.booking_service.entity.Inventory;
import com.adityaraj.booking_service.entity.Product;
import com.adityaraj.booking_service.repository.InventoryRepository;
import com.adityaraj.booking_service.repository.ProductRepository;
import com.adityaraj.booking_service.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;

    public InventoryServiceImpl(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository) {

        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;

    }

    @Override
    public InventoryResponse createInventory(InventoryRequest inventory) {

        if (inventory.getAvailableQuantity() > inventory.getTotalQuantity()){

            throw new RuntimeException("Inventory quantity exceeded");

        }

        Inventory newInventory = new Inventory();

        Product product = productRepository
                .findById(inventory.getProductId())
                .orElse(null);

        if (product == null) {
            return null;
        }

        newInventory.setProduct(product);
        newInventory.setTotalQuantity(inventory.getTotalQuantity());
        newInventory.setAvailableQuantity(inventory.getAvailableQuantity());
        newInventory.setActive(inventory.getActive());

        Inventory savedInventory = inventoryRepository.save(newInventory);

        return generateInventoryResponse(savedInventory);

    }

    @Override
    public InventoryResponse getInventoryById(UUID id) {

        Inventory inventory = inventoryRepository.findById(id).orElse(null);

        return inventory == null ? null : generateInventoryResponse(inventory);

    }

    @Override
    public InventoryResponse getInventoryByProductId(UUID productId) {

        Inventory inventory = inventoryRepository.findByProductId(productId).orElse(null);

       return inventory == null ? null : generateInventoryResponse(inventory);
    }

    @Override
    public List<InventoryResponse> getInventory() {

        List<Inventory> inventory = inventoryRepository.findAll();

        return inventory.stream()
                .map(this::generateInventoryResponse)
                .toList();

    }

    @Override
    public InventoryResponse updateInventory(UUID id, InventoryRequest inventory) {

        if (inventory.getAvailableQuantity() > inventory.getTotalQuantity()){
            throw new RuntimeException("Inventory quantity exceeded");
        }

        Inventory existingInventory = inventoryRepository
                .findById(id)
                .orElse(null);

        if (existingInventory == null) {
            return null;
        }

        Product product = productRepository
                .findById(inventory.getProductId())
                .orElse(null);

        if (product == null) {
            return null;
        }

        existingInventory.setProduct(product);
        existingInventory.setTotalQuantity(inventory.getTotalQuantity());
        existingInventory.setAvailableQuantity(inventory.getAvailableQuantity());
        existingInventory.setActive(inventory.getActive());

        Inventory savedInventory = inventoryRepository.save(existingInventory);

        return generateInventoryResponse(savedInventory);

    }

    @Override
    public void cancelInventory(UUID id) {

        Inventory inventory = inventoryRepository
                .findById(id)
                .orElse(null);

        if (inventory == null) {
            return;
        }

        inventory.setActive(false);

        inventoryRepository.save(inventory);

    }

    private InventoryResponse generateInventoryResponse(Inventory savedInventory) {

        InventoryResponse inventoryResponse = new InventoryResponse();

        inventoryResponse.setId(savedInventory.getId());
        inventoryResponse.setProduct(savedInventory.getProduct());
        inventoryResponse.setTotalQuantity(savedInventory.getTotalQuantity());
        inventoryResponse.setAvailableQuantity(savedInventory.getAvailableQuantity());
        inventoryResponse.setActive(savedInventory.getActive());

        return inventoryResponse;

    }

}
