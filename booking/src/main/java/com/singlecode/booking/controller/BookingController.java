package com.singlecode.booking.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.singlecode.booking.client.StockClient;
import com.singlecode.booking.dto.OrderDTO;
import com.singlecode.booking.entity.Order;
import com.singlecode.booking.repository.IOrderRepository;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private StockClient stockClient;

    @PostMapping("/order")
    @HystrixCommand(fallbackMethod = "fallbackToStockService")
    public String saveOrder(@RequestBody OrderDTO orderDTO) {

        boolean inStock = orderDTO.getOrderItems().stream()
                .allMatch(orderItem -> stockClient.strockAvailability(orderItem.getCode()));

        if(inStock) {
            Order order = new Order();

            order.setOrderNo(UUID.randomUUID().toString());
            order.setOrderItems(orderDTO.getOrderItems());

            orderRepository.save(order);

            return "Order Saved";
        }

        return "Order Cannot be Saved";
    }

    @SuppressWarnings("unused")
    private String fallbackToStockService() {
        return "Something went wrong, please try after some time";
    }
}
