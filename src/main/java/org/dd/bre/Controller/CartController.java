package org.dd.bre.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.*;
import org.dd.bre.Security.Service.CustomUserDetails;
import org.dd.bre.Service.CartService;
import org.dd.bre.Service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
@PreAuthorize("hasRole('CUSTOMER')")
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping("/me")
    public ResponseEntity<CartDTO> getMyCart(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.getCart(userDetails));
    }

    @PostMapping("summary")
    public ResponseEntity<OrderSummary> getMyCartSummary(@Valid @RequestBody List<PurchaseItem> items) {
        return ResponseEntity.ok(orderService.calculateOrderSummary(items));
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<CartDTO> updateItemQuantity(
            @PathVariable Long itemId,
            @Valid @RequestBody UpdateItemQuantityDto req,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.updateItemQuantity(userDetails, itemId, req));
    }

    @PostMapping("/items")
    public ResponseEntity<CartItemDTO> addItemToCart(
            @Valid @RequestBody AddItemToCartDto req,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.addItemToCart(userDetails, req));
    }


    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartDTO> deleteItem(@PathVariable Long itemId,
                                              @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(cartService.deleteItem(userDetails, itemId));
    }

    private UserDetails getAuthenticatedUser() {
        return new CustomUserDetails();
    }
}
