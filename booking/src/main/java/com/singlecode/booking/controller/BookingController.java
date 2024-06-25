package com.singlecode.booking.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.singlecode.booking.dto.OrderDTO;
import com.singlecode.booking.entity.Order;
import com.singlecode.booking.repository.IOrderRepository;


@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private IOrderRepository orderRepository;

    @PostMapping("/order")
    public String saveOrder(@RequestBody OrderDTO orderDTO) {
    
        Order order = new Order();
        order.setOrderNo(UUID.randomUUID().toString());
        order.setOrderItems(orderDTO.getOrderItems());
        orderRepository.save(order);

        return "Order saved successfully";
    }

}
