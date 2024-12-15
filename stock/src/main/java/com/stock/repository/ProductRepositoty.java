package com.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stock.entity.ProductEntity;

public interface ProductRepositoty extends JpaRepository<ProductEntity, String>{
    
}
