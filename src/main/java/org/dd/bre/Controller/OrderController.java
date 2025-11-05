package org.dd.bre.Controller;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.OrderHistoryDto;
import org.dd.bre.Dto.PurchaseReq;
import org.dd.bre.Service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/purchase")
    public OrderHistoryDto CreateOrder(@RequestBody PurchaseReq purchaseReq){
        Long userId = getAuthenticatedUserId();
        return orderService.createOrder(userId,purchaseReq);
    }
    private Long getAuthenticatedUserId() {
        return 1L;
    }
}
