package org.dd.bre.Controller;

import org.dd.bre.Service.WishListService;
import org.dd.bre.model.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getWishListHandler(){
        Integer userId = getAuthenticatedUserId().intValue();
        return new ResponseEntity<>(wishListService.getWishList(userId), HttpStatus.OK);
    }

    @PutMapping("/toggle/{productId}")
    public ResponseEntity<List<ProductDTO>>toggleWishList(@PathVariable Integer productId){
        Integer userId = getAuthenticatedUserId().intValue();
        return new ResponseEntity<>(wishListService.toggleWishList(userId,productId), HttpStatus.OK);
    }

    private Long getAuthenticatedUserId() {
        return 1L;
    }
}
