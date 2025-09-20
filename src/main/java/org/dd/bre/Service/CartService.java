package org.dd.bre.Service;

import org.dd.bre.Exception.CartItemNotFoundException;
import org.dd.bre.Exception.ProductNotFoundException;
import org.dd.bre.Repo.CartItemRepo;
import org.dd.bre.Repo.CartRepo;
import org.dd.bre.Repo.ProductVariantRepo;
import org.dd.bre.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Service
public class CartService {

    CartRepo cartRepo;
    CartItemRepo cartItemRepo;
    ProductVariantRepo productVariantRepo;

    public CartService(CartRepo cartRepo, CartItemRepo cartItemRepo, ProductVariantRepo productVariantRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
        this.productVariantRepo = productVariantRepo;
    }

    public CartDTO getCart(Long userId) {
        Cart cart = cartRepo.findByUserId(userId);

        if (cart == null) {
            throw new CartItemNotFoundException("Cart item not found");
        }

        return mapToCartDTO(cart);
    }

    private CartDTO mapToCartDTO(Cart cart) {
        return new CartDTO(
                LocalDateTime.now(),
                LocalDateTime.now(),
                cart.getItems().stream()
                        .map(this::mapToCartItemDTO)
                        .collect(Collectors.toList())
        );
    }

    private CartItemDTO mapToCartItemDTO(CartItem cartItem) {
        String imageUrl = cartItem.getProductVariant().getImages().stream()
                .map(Image::getUrl)
                .findFirst()
                .orElse("default-image.png");

        return new CartItemDTO(
                cartItem.getQuantity(),
                cartItem.getProductVariant().getProduct().getProductName(),
                imageUrl,
                cartItem.getProductVariant().getPrice(),
                cartItem.getProductVariant().getSize(),
                cartItem.getProductVariant().getColor()
        );
    }



    public void delCart(Long userId) {

        Cart cart = cartRepo.findByUserId(userId);

        if (cart == null) {
            throw new CartItemNotFoundException("Cart item not found");
        }
        cartRepo.delete(cart);
    }


    public void updateItemQuantity(Long userId, Long itemId, Integer quantity) {

        CartItem item = cartItemRepo.findByIdAndCart_User_Id(itemId, userId);
        if (item == null) {
            throw new CartItemNotFoundException("Cart item not found");
        }

        if (quantity > item.getProductVariant().getStockQty()) {
            throw new IllegalArgumentException("Quantity is greater than stock available");
        }

        item.setQuantity(quantity);
        cartItemRepo.save(item);
    }


    public void deleteItem(Long userId, Long itemId) {

        CartItem item = cartItemRepo.findByIdAndCart_User_Id(itemId, userId);
        if (item == null) {
            throw new CartItemNotFoundException("Cart item not found");
        }
        cartItemRepo.delete(item);
    }

    public void addItemToCart(Long userId, Long variantId) {
        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null) {
            throw new CartItemNotFoundException("Cart item not found");
        }

        ProductVariant variant = productVariantRepo.findById(variantId)
                .orElseThrow(() -> new ProductNotFoundException("Product variant not found"));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProductVariant().getId().equals(variant.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + 1;

            if (newQuantity > variant.getStockQty()) {
                throw new IllegalArgumentException("Quantity exceeds available stock");
            }

            existingItem.setQuantity(newQuantity);
            cartItemRepo.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProductVariant(variant);
            newItem.setQuantity(1);
            cartItemRepo.save(newItem);
        }
    }


}
