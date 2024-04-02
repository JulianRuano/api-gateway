package com.stock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.stock.entity.StockEntity;

public interface StockRepository  extends JpaRepository<StockEntity, Long>{
    Optional<StockEntity> findByCode(String code);
}
