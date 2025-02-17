package com.example.orderservice.orderservice.controller;

import com.example.orderservice.orderservice.dto.OrderDto;
import com.example.orderservice.orderservice.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order Service", description = "API для управления заказами")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Создать заказ", description = "Создаёт новый заказ")
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    @Operation(summary = "Получить список заказов", description = "Возвращает список заказов с пагинацией")
    public ResponseEntity<List<OrderDto>> getOrders(
            @Parameter(description = "Номер страницы") @RequestParam int page,
            @Parameter(description = "Размер страницы") @RequestParam int size
    ) {
        List<OrderDto> orders = orderService.getOrders(page, size);
        return ResponseEntity.ok(orders);
    }
}

