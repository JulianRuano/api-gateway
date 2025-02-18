package com.stock.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.dto.StockDto;
import com.stock.entity.StockEntity;
import com.stock.repository.StockRepository;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockRepository stockRepository;

    @GetMapping("/check/{code}")
    public ResponseEntity<String> checkStock(@PathVariable String code) {
        Optional<StockEntity> stock = stockRepository.findByCode(code);
        stock.orElseThrow(() -> new RuntimeException("Stock not found"+ code));

        if(stock.get().getQuantity() > 0) {
            return ResponseEntity.ok("Stock available");
        } else {
            return ResponseEntity.ok("Stock not available");
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<StockEntity> strockAvailability(@PathVariable String code) {
        Optional<StockEntity> stock = stockRepository.findByCode(code);
        stock.orElseThrow(() -> new RuntimeException("Stock not found"+ code));
        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/")
    public ResponseEntity<StockEntity> updateStock(@RequestBody StockDto stockDto) {
        Optional<StockEntity> stock = stockRepository.findByCode(stockDto.getCode());
        stock.orElseThrow(() -> new RuntimeException("Stock not found"+ stockDto.getCode()));
        stock.get().setQuantity(stock.get().getQuantity() - stockDto.getQuantity());
        stockRepository.save(stock.get());

        return ResponseEntity.ok(stock.get());
    }

    @PostMapping("/")
    public ResponseEntity<StockEntity> createStock(@RequestBody StockDto stockDto) {
        StockEntity stock = new StockEntity();

        stock.setCode(stockDto.getCode());
        stock.setQuantity(stockDto.getQuantity());
        stockRepository.save(stock);

        return ResponseEntity.ok(stock);
    }

    
}
