package org.dd.bre.Controller;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.ProductPageResponse;
import org.dd.bre.Service.WishListService;
import org.dd.bre.Dto.ProductDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishListController {

    private final WishListService wishListService;

    @GetMapping
    public ResponseEntity<ProductPageResponse> getWishListHandler(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "12") int size){
        Long userId = getAuthenticatedUserId();
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(wishListService.getWishList(userId,pageable), HttpStatus.OK);
    }

    @PutMapping("/toggle/{productId}")
    public ResponseEntity<ProductPageResponse>toggleWishList(@PathVariable Long productId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "12") int size){
        Long userId = getAuthenticatedUserId();
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(wishListService.toggleWishList(userId,productId,pageable), HttpStatus.OK);
    }

    private Long getAuthenticatedUserId() {
        return 1L;
    }
}
