package com.adityaraj.booking_service.controller;

import com.adityaraj.booking_service.dto.ProductRequest;
import com.adityaraj.booking_service.dto.ProductResponse;
import com.adityaraj.booking_service.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        ProductResponse productResponse = productService.newProduct(product);
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable UUID id) {
        ProductResponse productResponse = productService.getProductById(id);
        if(productResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/productType/{productTypeId}")
    public ResponseEntity<List<ProductResponse>> getAllProductsByType(@PathVariable Long productTypeId) {

        List<ProductResponse> productResponses = productService.getAllProductByType(productTypeId);
        if(productResponses == null) {
            return null;
        }
        return ResponseEntity.ok(productResponses);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        List<ProductResponse> productResponses = productService.getAllProduct();

        if(productResponses == null) {
            return null;
        }

        return ResponseEntity.ok(productResponses);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable UUID id,
                                                         @RequestBody ProductRequest product) {
        ProductResponse productResponse = productService.updateProduct(id, product);
        if(productResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ProductResponse> cancelProduct(@PathVariable UUID id) {

        ProductResponse productResponse = productService.getProductById(id);
        if(productResponse == null) {
            return ResponseEntity.notFound().build();
        }
        productService.cancelBooking(id);
        return ResponseEntity.ok(productResponse);
    }

}
