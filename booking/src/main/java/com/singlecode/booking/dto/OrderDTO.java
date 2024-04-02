package com.singlecode.booking.dto;

import java.util.List;

import com.singlecode.booking.entity.OrderItem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private List<OrderItem> orderItems;
}
