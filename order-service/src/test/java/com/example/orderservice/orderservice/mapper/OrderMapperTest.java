package com.example.orderservice.orderservice.mapper;

import com.example.orderservice.orderservice.dto.OrderDto;
import com.example.orderservice.orderservice.entity.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void testToEntity() {
        OrderDto dto = OrderDto.builder()
                .id(1L)
                .product("Test Product")
                .quantity(10)
                .price(BigDecimal.valueOf(99.99))
                .build();

        OrderEntity entity = orderMapper.toEntity(dto);

        assertNotNull(entity, "Mapped entity should not be null");
        assertEquals(dto.getId(), entity.getId(), "ID should match");
        assertEquals(dto.getProduct(), entity.getProduct(), "Product should match");
        assertEquals(dto.getQuantity(), entity.getQuantity(), "Quantity should match");
        assertEquals(dto.getPrice(), entity.getPrice(), "Price should match");
    }

    @Test
    void testToDto() {
        OrderEntity entity = OrderEntity.builder()
                .id(1L)
                .product("Test Product")
                .quantity(10)
                .price(BigDecimal.valueOf(99.99))
                .build();

        OrderDto dto = orderMapper.toDto(entity);

        assertNotNull(dto, "Mapped DTO should not be null");
        assertEquals(entity.getId(), dto.getId(), "ID should match");
        assertEquals(entity.getProduct(), dto.getProduct(), "Product should match");
        assertEquals(entity.getQuantity(), dto.getQuantity(), "Quantity should match");
        assertEquals(entity.getPrice(), dto.getPrice(), "Price should match");
    }

    @Test
    void testNullSafety() {
        assertNull(orderMapper.toEntity(null), "Mapping null DTO to entity should return null");
        assertNull(orderMapper.toDto(null), "Mapping null entity to DTO should return null");
    }
}
