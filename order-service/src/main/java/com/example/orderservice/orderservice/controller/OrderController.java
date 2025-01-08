package com.example.orderservice.orderservice.controller;

import com.example.orderservice.orderservice.dto.OrderDto;
import com.example.orderservice.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders(
            @RequestParam int page,
            @RequestParam int size
    ) {
        List<OrderDto> orders = orderService.getOrders(page, size);
        return ResponseEntity.ok(orders);
    }
}

