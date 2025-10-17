package org.dd.bre.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dd.bre.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderHistoryDto {
    LocalDateTime createdAt;
    Long id;
    BigDecimal totalPrice;
    OrderStatus status;
    List<OrderItemDto> items;


}
