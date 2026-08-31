package com.adityaraj.booking_service.controller;

import com.adityaraj.booking_service.dto.ProductTypeRequest;
import com.adityaraj.booking_service.dto.ProductTypeResponse;
import com.adityaraj.booking_service.entity.ProductType;
import com.adityaraj.booking_service.service.ProductTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-types")
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @PostMapping
    public ResponseEntity<ProductTypeResponse> createProductType(@RequestBody ProductType productType) {
        ProductTypeResponse productTypeResponse = productTypeService.createProductType(productType);
        return ResponseEntity.ok(productTypeResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTypeResponse> getProductType(@PathVariable Long id) {
        ProductTypeResponse productTypeResponse = productTypeService.getProductTypeById(id);
        if (productTypeResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productTypeResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductTypeResponse>> getAllProductTypes() {
        List<ProductTypeResponse> productTypeResponse = productTypeService.getProductType();
        if (productTypeResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productTypeResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductTypeResponse> updateProductType(
            @PathVariable Long id, @RequestBody ProductTypeRequest request) {

        ProductTypeResponse productTypeResponse = productTypeService.updateProductType(id, request);

        if (productTypeResponse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productTypeResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductType(@PathVariable Long id) {
        productTypeService.deleteProductType(id);
        return ResponseEntity.noContent().build();
    }
}
