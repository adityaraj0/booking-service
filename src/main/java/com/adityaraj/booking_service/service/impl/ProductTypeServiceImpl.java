package com.adityaraj.booking_service.service.impl;

import com.adityaraj.booking_service.dto.ProductTypeRequest;
import com.adityaraj.booking_service.dto.ProductTypeResponse;
import com.adityaraj.booking_service.entity.ProductType;
import com.adityaraj.booking_service.repository.ProductTypeRepository;
import com.adityaraj.booking_service.service.ProductTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProductTypeServiceImpl implements ProductTypeService {

    private ProductTypeRepository productTypeRepository;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public ProductTypeResponse createProductType(ProductType productType) {
        ProductType newProductType = new ProductType();
        newProductType.setName(productType.getName());

        ProductType savedProductType = productTypeRepository.save(newProductType);

        return generateProductTypeResponse(savedProductType);
    }

    @Override
    public ProductTypeResponse getProductTypeById(Long id) {

        ProductType productType = productTypeRepository.findById(id).orElse(null);

        return productType == null ? null : generateProductTypeResponse(productType);

    }

    @Override
    public List<ProductTypeResponse> getProductType() {

        List<ProductType> productTypes = productTypeRepository.findAll();

        return productTypes.stream()
                .map(this::generateProductTypeResponse)
                .toList();

    }

    @Override
    public ProductTypeResponse updateProductType(Long id, ProductTypeRequest request) {

        ProductType existingProductType = productTypeRepository.findById(id).orElse(null);

        if (existingProductType == null) {
            return null;
        }

        existingProductType.setName(request.getName());

        ProductType updatedProductType = productTypeRepository.save(existingProductType);
        return generateProductTypeResponse(updatedProductType);
    }

    @Override
    public void deleteProductType(Long id) {
        ProductType productType = productTypeRepository.findById(id).orElse(null);
        if (productType == null) {
            return;
        }
        productTypeRepository.delete(productType);
    }

    private ProductTypeResponse generateProductTypeResponse(ProductType savedProductType) {
        ProductTypeResponse productTypeResponse = new ProductTypeResponse();
        productTypeResponse.setId(savedProductType.getId());
        productTypeResponse.setName(savedProductType.getName());

        return productTypeResponse;
    }
}
