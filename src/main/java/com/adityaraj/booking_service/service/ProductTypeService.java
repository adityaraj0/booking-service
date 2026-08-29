package com.adityaraj.booking_service.service;

import com.adityaraj.booking_service.dto.ProductTypeRequest;
import com.adityaraj.booking_service.dto.ProductTypeResponse;
import com.adityaraj.booking_service.entity.ProductType;

import java.util.List;

public interface ProductTypeService {

    ProductTypeResponse createProductType(ProductType productType);

    ProductTypeResponse getProductTypeById(Long id);

    List<ProductTypeResponse> getProductType();

    ProductTypeResponse updateProductType(Long id, ProductTypeRequest request);

    void deleteProductType(Long id);

}
