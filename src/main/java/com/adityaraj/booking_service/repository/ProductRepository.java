package com.adityaraj.booking_service.repository;

import com.adityaraj.booking_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByProductTypeId(Long productTypeId);
}
