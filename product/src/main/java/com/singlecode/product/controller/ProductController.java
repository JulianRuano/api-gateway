package com.singlecode.product.controller;

import org.springframework.web.bind.annotation.RestController;
import com.singlecode.product.entity.ProductEntity;
import com.singlecode.product.repository.ProductRepositoty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    
    private final ProductRepositoty productRepository;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity createProduct(@RequestBody ProductEntity product) {
        return productRepository.save(product);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductEntity updateProduct(@PathVariable String id, @RequestBody ProductEntity product) {
        return productRepository.save(product);
    }
    
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
    }
}
