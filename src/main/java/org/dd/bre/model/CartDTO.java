package org.dd.bre.model;


import java.time.LocalDateTime;
import java.util.List;

public record CartDTO (

     LocalDateTime createdAt,
     LocalDateTime updatedAt,
     List<CartItemDTO> cartItems
){}
