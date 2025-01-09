package com.example.notificationservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {
    private Long id;
    private String product;
    private Integer quantity;
    private BigDecimal price;
}
