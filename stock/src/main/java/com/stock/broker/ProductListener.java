package com.stock.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.stock.repository.ProductRepositoty;
import com.stock.config.RabbitConfig;
import com.stock.dto.ProductDto;
import com.stock.entity.ProductEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductListener {

    private final ProductRepositoty productRepositoty;

    @RabbitListener(queues = RabbitConfig.PRODUCT_STOCK_QUEUE)
    public void processProductCreated(ProductEvent event) {
        switch(event.getEventType()) {
            case CREATED:
                createProduct((ProductDto) event.getProductDto());
                break;
            case UPDATED:
                updateProduct((ProductDto) event.getProductDto());
                break;
            case DELETED:
                deleteProduct(((ProductDto) event.getProductDto()).getId());
                break;
            default:
                break;
        }
    }

    
    private void createProduct(ProductDto productDto) {
        log.info("Product created: {}", productDto);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDto.getId());
        productEntity.setName(productDto.getName());
        productRepositoty.save(productEntity);
    }

    private void updateProduct(ProductDto productDto) {
        log.info("Product updated: {}", productDto);

        ProductEntity productEntity = productRepositoty
            .findById(productDto.getId())
            .orElseThrow(() -> new RuntimeException("Product not found"));
        productEntity.setName(productDto.getName());
        productRepositoty.save(productEntity);
    }

    private void deleteProduct(String productId) {
        log.info("Product deleted: {}", productId);
        productRepositoty.deleteById(productId);
    }

    
}
