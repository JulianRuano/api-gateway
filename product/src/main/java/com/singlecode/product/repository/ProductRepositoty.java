package com.singlecode.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.singlecode.product.entity.ProductEntity;

public interface ProductRepositoty extends JpaRepository<ProductEntity, String>{
    
}
