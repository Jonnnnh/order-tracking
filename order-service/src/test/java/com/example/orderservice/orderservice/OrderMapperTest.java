package com.example.orderservice.orderservice;

import com.example.orderservice.orderservice.dto.OrderDto;
import com.example.orderservice.orderservice.entity.OrderEntity;
import com.example.orderservice.orderservice.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderMapperTest {
    private final OrderMapper mapper = Mappers.getMapper(OrderMapper.class);

    @Test
    void testEntityToDto() {
        OrderEntity entity = OrderEntity.builder()
                .product("Laptop")
                .quantity(1)
                .price(new BigDecimal("1500.00"))
                .build();

        OrderDto dto = mapper.toDto(entity);

        assertNotNull(dto);
        assertEquals("Laptop", dto.getProduct());
        assertEquals(1, dto.getQuantity());
        assertEquals(new BigDecimal("1500.00"), dto.getPrice());
    }

    @Test
    void testDtoToEntity() {
        OrderDto dto = OrderDto.builder()
                .product("Laptop")
                .quantity(1)
                .price(new BigDecimal("1500.00"))
                .build();

        OrderEntity entity = mapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals("Laptop", entity.getProduct());
        assertEquals(1, entity.getQuantity());
        assertEquals(new BigDecimal("1500.00"), entity.getPrice());
    }
}
