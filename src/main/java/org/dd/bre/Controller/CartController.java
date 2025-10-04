package org.dd.bre.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.AddItemToCartDto;
import org.dd.bre.Dto.CartItemDTO;
import org.dd.bre.Dto.UpdateItemQuantityDto;
import org.dd.bre.Service.CartService;
import org.dd.bre.Dto.CartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/me")
    public ResponseEntity<CartDTO> getMyCart() {
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(cartService.getCart(userId));
    }


    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyCart() {
        Long userId = getAuthenticatedUserId();
        cartService.deleteCart(userId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartDTO> updateItemQuantity(
            @PathVariable Long itemId,
            @Valid @RequestBody UpdateItemQuantityDto req) {
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(cartService.updateItemQuantity(userId, itemId, req));
    }

    @PostMapping("/items")
    public ResponseEntity<CartItemDTO> addItemToCart(
            @Valid @RequestBody AddItemToCartDto req) {
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(cartService.addItemToCart(userId, req));
    }


    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartDTO> deleteItem(@PathVariable Long itemId) {
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(cartService.deleteItem(userId, itemId));
    }


    private Long getAuthenticatedUserId() {
        return 1L;
    }
}
