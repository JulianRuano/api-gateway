package com.stock.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.entity.StockEntity;
import com.stock.repository.StockRepository;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @RequestMapping("/{code}")
    public boolean strockAvailability(@PathVariable String code) {
        Optional<StockEntity> stock = stockRepository.findByCode(code);

        stock.orElseThrow(() -> new RuntimeException("Stock not found"+ code));

        return stock.get().getQuantity() > 0;
    }
    
}
