package org.dd.bre.Service;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.AddItemToCartDto;
import org.dd.bre.Dto.CartDTO;
import org.dd.bre.Dto.CartItemDTO;
import org.dd.bre.Dto.UpdateItemQuantityDto;
import org.dd.bre.Exception.CartItemNotFoundException;
import org.dd.bre.Exception.CartNotFoundException;
import org.dd.bre.Exception.ProductVariantNotFoundException;
import org.dd.bre.Mapper.CartMapper;
import org.dd.bre.Repo.CartItemRepo;
import org.dd.bre.Repo.CartRepo;
import org.dd.bre.Repo.ProductVariantRepo;
import org.dd.bre.model.*;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;
    private final ProductVariantRepo productVariantRepo;
    private final CartMapper cartMapper;


    public CartDTO getCart(Long userId) {
        Cart cart = cartRepo.findByUserId(userId);

        if (cart == null) {
            throw new CartNotFoundException("Cart not found");
        }

        return cartMapper.mapToCartDTO(cart);
    }




    public void deleteCart(Long userId) {

        Cart cart = cartRepo.findByUserId(userId);

        if (cart == null) {
            throw new CartItemNotFoundException("Cart item not found");
        }
        cartRepo.delete(cart);
    }


    public CartDTO updateItemQuantity(Long userId, Long itemId, UpdateItemQuantityDto req) {

        CartItem item = cartItemRepo.findByIdAndCart_User_Id(itemId, userId);
        if (item == null) {
            throw new CartItemNotFoundException("Cart item not found");
        }

        if (req.getQuantity() > item.getProductVariant().getStockQty()) {
            throw new IllegalArgumentException("Quantity is greater than stock available");
        }

        item.setQuantity(req.getQuantity());
        cartItemRepo.save(item);

        return cartMapper.mapToCartDTO(item.getCart());
    }


    public CartDTO deleteItem(Long userId, Long itemId) {

        CartItem item = cartItemRepo.findByIdAndCart_User_Id(itemId, userId);
        if (item == null) {
            throw new CartItemNotFoundException("Cart item not found");
        }
        cartItemRepo.delete(item);
        return cartMapper.mapToCartDTO(item.getCart());
    }

    public CartItemDTO addItemToCart(Long userId, AddItemToCartDto req) {

        Cart cart = cartRepo.findByUserId(userId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found");
        }

        ProductVariant variant = productVariantRepo.findById(req.getVariantId())
                .orElseThrow(() -> new ProductVariantNotFoundException("Product variant not found"));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProductVariant().getId().equals(variant.getId()))
                .findFirst()
                .orElse(null);
        CartItem item = new CartItem();
        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + req.getQuantity();

            if (newQuantity > variant.getStockQty()) {
                throw new IllegalArgumentException("Quantity exceeds available stock");
            }

            existingItem.setQuantity(newQuantity);
            cartItemRepo.save(existingItem);
            item = existingItem;
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProductVariant(variant);
            newItem.setQuantity(req.getQuantity());
            cartItemRepo.save(newItem);
            item = newItem;
        }
        return cartMapper.mapToCartItemDTO(item);
    }


}
