package org.dd.bre.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemDto {
    Integer quantity;
    BigDecimal subtotal;
    String productName;
    String imageUrl;
    BigDecimal price;
    String size;
    String color;
}
