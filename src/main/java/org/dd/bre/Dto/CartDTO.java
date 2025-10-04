package org.dd.bre.Dto;

import java.time.LocalDateTime;
import java.util.List;

public record CartDTO (
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<CartItemDTO> cartItems
){}
