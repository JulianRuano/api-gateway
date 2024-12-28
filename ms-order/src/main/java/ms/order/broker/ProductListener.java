package ms.order.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import ms.order.config.RabbitConfig;
import ms.order.dto.ProductDto;
import ms.order.entity.ProductEntity;
import ms.order.repository.IProductRepository;


@Service
@RequiredArgsConstructor
public class ProductListener {

    private final IProductRepository productRepositoty;

    @RabbitListener(queues = RabbitConfig.PRODUCT_CREATED_QUEUE)
    public void processProductCreated(ProductDto product) {
        
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());

        productRepositoty.save(productEntity);
        System.out.println("Product Created: " + product.getId() + " - " + product.getName());
    }
    
}
