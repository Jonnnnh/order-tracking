package com.example.orderservice.orderservice.mapper;

import com.example.orderservice.orderservice.dto.OrderDto;
import com.example.orderservice.orderservice.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto toDto(OrderEntity entity) {
        if (entity == null) {
            return null;
        }

        OrderDto orderDto = new OrderDto();
        orderDto.setId(entity.getId());
        orderDto.setProduct(entity.getProduct());
        orderDto.setQuantity(entity.getQuantity());
        orderDto.setPrice(entity.getPrice());

        return orderDto;
    }

    @Override
    public OrderEntity toEntity(OrderDto dto) {
        if (dto == null) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(dto.getId());
        orderEntity.setProduct(dto.getProduct());
        orderEntity.setQuantity(dto.getQuantity());
        orderEntity.setPrice(dto.getPrice());

        return orderEntity;
    }
}
