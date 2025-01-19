package com.stock.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.dto.ProductDto;
import com.stock.entity.ProductEntity;
import com.stock.repository.ProductRepositoty;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class ProductController {
    
    private final ProductRepositoty productRepository;

    @GetMapping("/products")
    public ResponseEntity<Iterable<ProductDto>> getAllProducts() {
        List<ProductEntity> products = productRepository.findAll();

        List<ProductDto> productDtos = products.stream()
            .map(product -> {
                ProductDto productDto = new ProductDto();
                productDto.setId(product.getId());
                productDto.setName(product.getName());
                return productDto;
            })
            .collect(Collectors.toList());

        return ResponseEntity.ok(productDtos);
    }

}
