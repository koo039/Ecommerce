package org.dd.bre.model;

import java.math.BigDecimal;

public record CartItemDTO (
        Integer quantity,
        String productName,
        String imageUrl,
        BigDecimal price,
        Size size,
        Color color
){}
