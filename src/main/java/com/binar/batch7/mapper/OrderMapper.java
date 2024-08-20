package com.binar.batch7.mapper;

import com.binar.batch7.dto.OrderDetailResponse;
import com.binar.batch7.dto.OrderResponse;
import com.binar.batch7.entity.Order;
import com.binar.batch7.entity.OrderDetail;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .orderTime(order.getOrderTime())
                .destinationAddress(order.getDestinationAddress())
                .isComplete(order.getIsComplete())
                .build();
    }

    public OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {
        return OrderDetailResponse.builder()
                .id(orderDetail.getId())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .build();
    }
}
