package com.singlecode.product.broker;

import com.singlecode.product.config.RabbitConfig;
import com.singlecode.product.dto.ProductDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;


@Component
public class ProductPublisher implements ProductEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public ProductPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publishProductCreated(ProductDto product) {
        ProductEvent productEvent = new ProductEvent(product, ProductEventType.CREATED);
        System.out.println("Publishing product created event: " + product.getId() + " - " + product.getName());
        rabbitTemplate.convertAndSend(RabbitConfig.PRODUCT_EXCHANGE, "", productEvent);
    }

    @Override
    public void publishProductUpdated(ProductDto product) {
        ProductEvent  productEvent = new ProductEvent(product, ProductEventType.UPDATED);
        System.out.println("Publishing product updated event: " + product);
        rabbitTemplate.convertAndSend(RabbitConfig.PRODUCT_EXCHANGE, "", productEvent);
    }

    @Override
    public void publishProductDeleted(String productId) {
        ProductDto product = new ProductDto();
        product.setId(productId);

        ProductEvent productEvent = new ProductEvent(product, ProductEventType.DELETED);
        System.out.println("Publishing product deleted event: " + productId);
        rabbitTemplate.convertAndSend(RabbitConfig.PRODUCT_EXCHANGE, "", productEvent);
    }
    
}
