package com.singlecode.product.controller;

import org.springframework.web.bind.annotation.RestController;

import com.singlecode.product.entity.ProductEntity;
import com.singlecode.product.repository.ProductRepositoty;
import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepositoty productRepository;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ProductEntity createProduct(@RequestBody ProductEntity product) {
        return productRepository.save(product);
    }
    
}
