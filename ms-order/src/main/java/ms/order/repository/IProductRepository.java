package ms.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ms.order.entity.ProductEntity;

public interface IProductRepository extends JpaRepository<ProductEntity, String> {
    
}
