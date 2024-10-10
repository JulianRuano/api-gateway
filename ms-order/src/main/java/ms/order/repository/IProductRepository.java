package ms.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ms.order.entity.Product;

public interface IProductRepository extends JpaRepository<Product, String> {
    
}
