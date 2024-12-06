package ms.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ms.order.entity.OrderEntity;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    
}
