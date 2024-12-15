package com.stock.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.stock.repository.ProductRepositoty;
import com.stock.config.RabbitConfig;
import com.stock.dto.ProductDto;
import com.stock.entity.ProductEntity;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductListener {

    private final ProductRepositoty productRepositoty;

    @RabbitListener(queues = RabbitConfig.PRODUCT_CREATED_QUEUE)
    public void processProductCreated(ProductDto product) {
        
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());

        productRepositoty.save(productEntity);
        System.out.println("Product Created: " + product.getId() + " - " + product.getName());
    }
    
}
