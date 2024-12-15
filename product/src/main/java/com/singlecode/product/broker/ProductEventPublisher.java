package com.singlecode.product.broker;

import com.singlecode.product.dto.ProductDto;

public interface ProductEventPublisher {
    void publishProductCreated(ProductDto product);
    void publishProductUpdated(ProductDto product);
    void publishProductDeleted(String productId);
}
