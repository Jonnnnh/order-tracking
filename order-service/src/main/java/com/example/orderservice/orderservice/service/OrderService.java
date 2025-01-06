package com.example.orderservice.orderservice.service;

import com.example.orderservice.orderservice.mapper.OrderMapper;
import com.example.orderservice.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

}
