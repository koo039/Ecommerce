package org.dd.bre.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.*;
import org.dd.bre.Service.CartService;
import org.dd.bre.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping("/me")
    public ResponseEntity<CartDTO> getMyCart() {
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PostMapping("summary")
    public ResponseEntity<OrderSummary> getMyCartSummary(@RequestBody List<PurchaseItem> items) {
        return ResponseEntity.ok(orderService.calculateOrderSummary(items));
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> clearMyCart() {
        Long userId = getAuthenticatedUserId();
        cartService.clearCart(userId);
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
