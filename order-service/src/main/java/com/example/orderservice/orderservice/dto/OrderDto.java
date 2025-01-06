package com.example.orderservice.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String product;
    private Integer quantity;
    private BigDecimal price;
}
