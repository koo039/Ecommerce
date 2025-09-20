package org.dd.bre.Controller;

import jakarta.validation.Valid;
import org.dd.bre.Service.CartService;
import org.dd.bre.model.CartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("/me")
    public ResponseEntity<CartDTO> getMyCart() {
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(cartService.getCart(userId));
    }


    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteMyCart() {
        Long userId = getAuthenticatedUserId();
        cartService.delCart(userId);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/items/{itemId}")
    public ResponseEntity<Void> updateItemQuantity(
            @PathVariable Long itemId,
            @Valid @RequestBody Integer quantity) {
        Long userId = getAuthenticatedUserId();
        cartService.updateItemQuantity(userId, itemId, quantity);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> addItemToCart(
            @Valid @RequestBody Long variantId) {
        Long userId = getAuthenticatedUserId();
        cartService.addItemToCart(userId, variantId);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
        Long userId = getAuthenticatedUserId();
        cartService.deleteItem(userId, itemId);
        return ResponseEntity.noContent().build();
    }


    private Long getAuthenticatedUserId() {
        return 1L;
    }
}
