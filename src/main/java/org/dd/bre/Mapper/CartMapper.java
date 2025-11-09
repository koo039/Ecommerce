package org.dd.bre.Mapper;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.CartDTO;
import org.dd.bre.Dto.CartItemDTO;
import org.dd.bre.Service.CartService;
import org.dd.bre.model.Cart;
import org.dd.bre.model.CartItem;
import org.dd.bre.model.Image;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class CartMapper {


    public CartDTO mapToCartDTO(Cart cart,int maxCartItems) {
        return new CartDTO(
                cart.getCreatedAt(),
                cart.getUpdatedAt(),
                maxCartItems,
                cart.getItems().stream()
                        .map(this::mapToCartItemDTO)
                        .collect(Collectors.toList())
        );
    }

    public CartItemDTO mapToCartItemDTO(CartItem cartItem) {
        String imageUrl = cartItem.getProductVariant().getImages().stream()
                .map(Image::getUrl)
                .findFirst()
                .orElse(null);

        return new CartItemDTO(
                cartItem.getId(),
                cartItem.getQuantity(),
                cartItem.getProductVariant().getProduct().getProductName(),
                cartItem.getProductVariant().getId(),
                imageUrl,
                cartItem.getProductVariant().getProduct().getPrice(),
                cartItem.getProductVariant().getSize(),
                cartItem.getProductVariant().getColor()
        );
    }
}
