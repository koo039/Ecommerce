package org.dd.bre.Mapper;

import org.dd.bre.Dto.OrderHistoryDto;
import org.dd.bre.Dto.OrderItemDto;
import org.dd.bre.model.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderHistoryMapper {

    public OrderHistoryDto mapToOrderHistoryDTO(Order order) {
        return new OrderHistoryDto(
                order.getCreatedAt(),
                order.getId(),
                order.getTotalPrice(),
                order.getStatus().getStatusName(),
                order.getItems().stream()
                        .map(this::mapToOrderItemDTO)
                        .collect(Collectors.toList())
        );
    }

    public OrderItemDto mapToOrderItemDTO(OrderItem orderItem) {
        String imageUrl = orderItem.getProductVariant().getImages().stream()
                .map(Image::getUrl)
                .findFirst()
                .orElse(null);

        return new OrderItemDto(
                orderItem.getQuantity(),
                orderItem.getSubtotal(),
                orderItem.getProductVariant().getProduct().getProductName(),
                imageUrl,
                orderItem.getProductVariant().getProduct().getPrice(),
                orderItem.getProductVariant().getSize(),
                orderItem.getProductVariant().getColor()
        );
    }
}
