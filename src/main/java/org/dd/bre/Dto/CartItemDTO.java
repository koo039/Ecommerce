package org.dd.bre.Dto;

import java.math.BigDecimal;
public record CartItemDTO (
        Long id,
        Integer quantity,
        String productName,
        Long variantId,
        String imageUrl,
        BigDecimal price,
        String size,
        String color
){}
