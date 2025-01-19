package ms.order.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ms.order.dto.OrderDto;
import ms.order.dto.OrderItemDto;
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
    public ResponseEntity<OrderDto> createProduct(@RequestBody OrderDto order) {
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

        orderRepository.save(newOrder);
        
        return ResponseEntity.ok(order);  
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<OrderDto>> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        
        List<OrderDto> orderDtos = orders.stream().map(order -> {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setOrderNo(order.getOrderNo());
            orderDto.setOrderDate(order.getOrderDate());
            orderDto.setOrderItems(order.getOrderItems().stream().map(orderItem -> {
                return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getQuantity());
            }).toList());
            return orderDto;
        }).toList();

        return ResponseEntity.ok(orderDtos);
    }
}
