package com.example.orderservice.orderservice.mapper;

import com.example.orderservice.orderservice.dto.OrderDto;
import com.example.orderservice.orderservice.entity.OrderEntity;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = false))
public interface OrderMapper {
    OrderDto toDto(OrderEntity entity);

    OrderEntity toEntity(OrderDto dto);
}

