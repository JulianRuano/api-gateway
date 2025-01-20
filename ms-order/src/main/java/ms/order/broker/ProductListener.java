package ms.order.broker;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.order.config.RabbitConfig;
import ms.order.dto.ProductDto;
import ms.order.entity.ProductEntity;
import ms.order.repository.IProductRepository;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductListener {

    private final IProductRepository productRepositoty;

    @RabbitListener(queues = RabbitConfig.PRODUCT_ORDER_QUEUE)
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
