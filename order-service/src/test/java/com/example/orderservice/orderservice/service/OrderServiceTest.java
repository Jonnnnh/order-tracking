package com.example.orderservice.orderservice.service;

import com.example.orderservice.orderservice.dto.OrderDto;
import com.example.orderservice.orderservice.entity.OrderEntity;
import com.example.orderservice.orderservice.kafka.OrderProducer;
import com.example.orderservice.orderservice.mapper.OrderMapper;
import com.example.orderservice.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;
    private OrderProducer orderProducer;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = mock(OrderRepository.class);
        orderMapper = mock(OrderMapper.class);
        orderProducer = mock(OrderProducer.class);
        orderService = new OrderService(orderRepository, orderMapper, orderProducer);
    }

    @Test
    void createOrder_success() {
        OrderDto inputDto = new OrderDto();
        inputDto.setProduct("Laptop");
        inputDto.setQuantity(1);
        inputDto.setPrice(new BigDecimal("1500.00"));

        OrderEntity mappedEntity = new OrderEntity();
        mappedEntity.setId(1L);
        mappedEntity.setProduct("Laptop");
        mappedEntity.setQuantity(1);
        mappedEntity.setPrice(new BigDecimal("1500.00"));

        OrderEntity savedEntity = new OrderEntity();
        savedEntity.setId(1L);
        savedEntity.setProduct("Laptop");
        savedEntity.setQuantity(1);
        savedEntity.setPrice(new BigDecimal("1500.00"));

        OrderDto outputDto = new OrderDto();
        outputDto.setId(1L);
        outputDto.setProduct("Laptop");
        outputDto.setQuantity(1);
        outputDto.setPrice(new BigDecimal("1500.00"));

        when(orderMapper.toEntity(inputDto)).thenReturn(mappedEntity);
        when(orderRepository.save(mappedEntity)).thenReturn(savedEntity);
        when(orderMapper.toDto(savedEntity)).thenReturn(outputDto);

        OrderDto result = orderService.createOrder(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Laptop", result.getProduct());
        assertEquals(1, result.getQuantity());
        assertEquals(new BigDecimal("1500.00"), result.getPrice());

        verify(orderMapper, times(1)).toEntity(inputDto);
        verify(orderRepository, times(1)).save(mappedEntity);
        verify(orderMapper, times(1)).toDto(savedEntity);
        verify(orderProducer, times(1)).sendOrderCreatedEvent(outputDto);
    }

    @Test
    void createOrder_invalidInput_throwsException() {
        OrderDto inputDto = new OrderDto();
        inputDto.setProduct("");
        inputDto.setQuantity(0);
        inputDto.setPrice(BigDecimal.ZERO);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrder(inputDto);
        });

        assertEquals("Product name must not be null or empty", exception.getMessage());
    }

    @Test
    void createOrder_nullInput_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.createOrder(null);
        });

        assertEquals("OrderDto must not be null", exception.getMessage());
    }
}
