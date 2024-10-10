package ms.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ms.order.entity.Order;

public interface IOrderRepository extends JpaRepository<Order, Long> {
    
}
