package ms.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ms.order.dto.OrderDto;
import ms.order.entity.OrderEntity;
import ms.order.entity.OrderItemEntity;
import ms.order.repository.IOrderRepository;
import ms.order.repository.IProductRepository;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final IProductRepository productRepository;
    private final IOrderRepository orderRepository;

    @PostMapping("/create")
    public ResponseEntity<OrderEntity> createProduct(@RequestBody OrderDto order) {
        OrderEntity newOrder = new OrderEntity();
        newOrder.setOrderNo(order.getOrderNo());
        newOrder.setOrderDate(order.getOrderDate());

        order.getOrderItems().forEach(orderItemDto -> {
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrder(newOrder);
            orderItem.setProduct(productRepository.findById(orderItemDto.getProductId()).get());
            orderItem.setQuantity(orderItemDto.getQuantity());
            newOrder.getOrderItems().add(orderItem);
        });

        return ResponseEntity.ok(orderRepository.save(newOrder));
       
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<OrderEntity>> getAllOrders() {
        return ResponseEntity.ok(orderRepository.findAll());
    }
}
