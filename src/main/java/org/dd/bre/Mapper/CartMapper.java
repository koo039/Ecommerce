package org.dd.bre.Mapper;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.CartDTO;
import org.dd.bre.Dto.CartItemDTO;
import org.dd.bre.model.Cart;
import org.dd.bre.model.CartItem;
import org.dd.bre.model.Image;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartMapper {

    public CartDTO mapToCartDTO(Cart cart) {
        return new CartDTO(
                LocalDateTime.now(),
                LocalDateTime.now(),
                cart.getItems().stream()
                        .map(this::mapToCartItemDTO)
                        .collect(Collectors.toList())
        );
    }

    public CartItemDTO mapToCartItemDTO(CartItem cartItem) {
        String imageUrl = cartItem.getProductVariant().getImages().stream()
                .map(Image::getUrl)
                .findFirst()
                .orElse("default-image.png");

        return new CartItemDTO(
                cartItem.getId(),
                cartItem.getQuantity(),
                cartItem.getProductVariant().getProduct().getProductName(),
                imageUrl,
                cartItem.getProductVariant().getProduct().getPrice(),
                cartItem.getProductVariant().getSize(),
                cartItem.getProductVariant().getColor()
        );
    }
}
