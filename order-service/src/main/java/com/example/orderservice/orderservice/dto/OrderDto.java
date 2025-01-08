package com.example.orderservice.orderservice.dto;

import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;

    @NotBlank(message = "Product name must not be empty")
    private String product;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private Integer quantity;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;
}
