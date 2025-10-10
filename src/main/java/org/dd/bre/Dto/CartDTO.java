package org.dd.bre.Dto;

import jakarta.validation.constraints.Max;

import java.time.LocalDateTime;
import java.util.List;

public record CartDTO (
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        int maxCartItems,
        List<CartItemDTO> cartItems
){}
