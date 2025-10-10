package org.dd.bre.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.OrderPageResponse;
import org.dd.bre.Dto.UserProfileDto;
import org.dd.bre.Service.OrderService;
import org.dd.bre.Service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDto> getUserProfileHandler() {
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDto> updateProfile(@Valid @RequestBody UserProfileDto user){
        Long userId = getAuthenticatedUserId();
        return ResponseEntity.ok().body(userService.updateProfile(userId,user));
    }

    @GetMapping("/orders")
    public ResponseEntity<OrderPageResponse> getOrdersHistoryHandler(@RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "2") int size) {
        Long userId = getAuthenticatedUserId();
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(orderService.getOrdersHistory(userId,pageable));
    }

    private Long getAuthenticatedUserId() {
        return 1L;
    }
}
