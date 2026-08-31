package com.adityaraj.booking_service.service;

import com.adityaraj.booking_service.dto.ProductRequest;
import com.adityaraj.booking_service.dto.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse newProduct(ProductRequest product);

    ProductResponse getProductById(UUID id);


    List<ProductResponse> getAllProductByType(Long productTypeId);

    List<ProductResponse> getAllProduct();

    ProductResponse updateProduct(UUID id, ProductRequest product);

    void cancelBooking(UUID id);


}
