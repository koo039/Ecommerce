package org.dd.bre.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderHistoryDto {
    LocalDateTime createdAt;
    Long id;
    BigDecimal totalPrice;
    String status;
    List<OrderItemDto> items;


}
