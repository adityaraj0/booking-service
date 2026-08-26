package com.adityaraj.booking_service.repository;

import com.adityaraj.booking_service.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

}
