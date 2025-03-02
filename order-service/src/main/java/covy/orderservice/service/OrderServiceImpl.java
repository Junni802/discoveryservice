package covy.orderservice.service;

import covy.orderservice.dto.OrderDto;
import covy.orderservice.entity.OrderEntity;

public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDto createOrder(OrderDto orderDetails) {
            return null;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        return null;
    }

    @Override
    public Iterable<OrderEntity> getOrdersByUserId(String userId) {
        return null;
    }
}
