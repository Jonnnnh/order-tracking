package com.example.orderservice.orderservice.service;

import com.example.orderservice.orderservice.dto.OrderDto;
import com.example.orderservice.orderservice.entity.OrderEntity;
import com.example.orderservice.orderservice.kafka.OrderProducer;
import com.example.orderservice.orderservice.mapper.OrderMapper;
import com.example.orderservice.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        OrderEntity orderEntity = orderMapper.toEntity(orderDto);
        orderEntity = orderRepository.save(orderEntity);
        OrderDto createdOrder = orderMapper.toDto(orderEntity);
        orderProducer.sendOrderCreatedEvent(createdOrder);

        return createdOrder;
    }

}
