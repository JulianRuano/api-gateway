package com.singlecode.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.singlecode.booking.entity.Order;


public interface IOrderRepository extends JpaRepository<Order, Long> {
    
}
