package com.example.orderservice.orderservice.service;

import com.example.orderservice.orderservice.dto.OrderDto;
import com.example.orderservice.orderservice.entity.OrderEntity;
import com.example.orderservice.orderservice.kafka.OrderProducer;
import com.example.orderservice.orderservice.mapper.OrderMapper;
import com.example.orderservice.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderProducer orderProducer;

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        validateOrderDto(orderDto);
        OrderEntity orderEntity = orderMapper.toEntity(orderDto);
        orderEntity = orderRepository.save(orderEntity);
        OrderDto createdOrder = orderMapper.toDto(orderEntity);
        orderProducer.sendOrderCreatedEvent(createdOrder);

        return createdOrder;
    }

    private void validateOrderDto(OrderDto orderDto) {
        if (orderDto == null) {
            throw new IllegalArgumentException("OrderDto must not be null");
        }
        if (orderDto.getProduct() == null || orderDto.getProduct().isEmpty()) {
            throw new IllegalArgumentException("Product name must not be null or empty");
        }
        if (orderDto.getQuantity() == null || orderDto.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        if (orderDto.getPrice() == null || orderDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getOrders(int page, int size) {
        Page<OrderEntity> ordersPage = orderRepository.findAll(PageRequest.of(page, size));
        return ordersPage.getContent()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

}
