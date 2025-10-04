package org.dd.bre.Controller;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Service.WishListService;
import org.dd.bre.Dto.ProductDTO;
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
    public ResponseEntity<List<ProductDTO>> getWishListHandler(){
        Long userId = getAuthenticatedUserId();
        return new ResponseEntity<>(wishListService.getWishList(userId), HttpStatus.OK);
    }

    @PutMapping("/toggle/{productId}")
    public ResponseEntity<List<ProductDTO>>toggleWishList(@PathVariable Long productId){
        Long userId = getAuthenticatedUserId();
        return new ResponseEntity<>(wishListService.toggleWishList(userId,productId), HttpStatus.OK);
    }

    private Long getAuthenticatedUserId() {
        return 1L;
    }
}
