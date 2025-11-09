package org.dd.bre.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.OrderHistoryDto;
import org.dd.bre.Dto.PurchaseReq;
import org.dd.bre.Service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('CUSTOMER')")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/purchase")
    public OrderHistoryDto CreateOrder(@Valid @RequestBody PurchaseReq purchaseReq,
                                       @AuthenticationPrincipal UserDetails userDetails){
        return orderService.createOrder(userDetails,purchaseReq);
    }
}
