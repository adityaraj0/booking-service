package com.adityaraj.booking_service.service.impl;

import com.adityaraj.booking_service.dto.ProductRequest;
import com.adityaraj.booking_service.dto.ProductResponse;
import com.adityaraj.booking_service.entity.Product;
import com.adityaraj.booking_service.entity.ProductType;
import com.adityaraj.booking_service.repository.ProductRepository;
import com.adityaraj.booking_service.repository.ProductTypeRepository;
import com.adityaraj.booking_service.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductTypeRepository productTypeRepository;

    public ProductServiceImpl(
            ProductRepository productRepository,
            ProductTypeRepository productTypeRepository) {

        this.productRepository = productRepository;
        this.productTypeRepository = productTypeRepository;

    }

    @Override
    public ProductResponse newProduct(ProductRequest product) {

        Product newProduct = new Product();

        newProduct.setName(product.getName());
        newProduct.setDescription(product.getDescription());
        newProduct.setAmount(product.getAmount());

        ProductType productType = productTypeRepository.getReferenceById(product.getProductTypeId());
        newProduct.setProductType(productType);
        newProduct.setActive(product.getActive());

        Product savedProduct = productRepository.save(newProduct);
        return generateProductResponse(savedProduct);

    }

    @Override
    public ProductResponse getProductById(UUID id) {

        Product product = productRepository.findById(id).orElse(null);

        return product == null ? null : generateProductResponse(product);
    }


    @Override
    public List<ProductResponse> getAllProductByType(Long productTypeId) {

        List<Product> product = productRepository.findByProductTypeId(productTypeId);

        return product.stream()
                .map(this::generateProductResponse)
                .toList();

    }

    @Override
    public List<ProductResponse> getAllProduct() {

        List<Product> product = productRepository.findAll();

        return product.stream()
                .map(this::generateProductResponse)
                .toList();

    }

    @Override
    public ProductResponse updateProduct(UUID id, ProductRequest product) {

        Product existingProduct = productRepository.findById(id).orElse(null);
        if(existingProduct == null){
            return null;
        }
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setAmount(product.getAmount());

        ProductType productType = productTypeRepository.getReferenceById(product.getProductTypeId());
        existingProduct.setProductType(productType);
        existingProduct.setActive(product.getActive());

        Product updateProduct = productRepository.save(existingProduct);
        return generateProductResponse(updateProduct);

    }

    @Override
    public void cancelBooking(UUID id) {

        Product product = productRepository.findById(id).orElse(null);

        if(product == null){
            return;
        }

        product.setActive(false);

        productRepository.save(product);

    }


    private ProductResponse generateProductResponse(Product newProduct) {

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(newProduct.getId());
        productResponse.setName(newProduct.getName());
        productResponse.setDescription(newProduct.getDescription());
        productResponse.setAmount(newProduct.getAmount());
        productResponse.setProductType(newProduct.getProductType());
        productResponse.setActive(newProduct.getActive());

        return productResponse;
    }

}
