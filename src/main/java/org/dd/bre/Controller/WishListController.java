package org.dd.bre.Controller;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.ProductPageResponse;
import org.dd.bre.Service.WishListService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('CUSTOMER')")
public class WishListController {

    private final WishListService wishListService;

    @GetMapping
    public ResponseEntity<ProductPageResponse> getWishListHandler(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "12") int size,
                                                                  @AuthenticationPrincipal UserDetails userDetails){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(wishListService.getWishList(userDetails,pageable), HttpStatus.OK);
    }

    @PutMapping("/toggle/{productId}")
    public ResponseEntity<ProductPageResponse>toggleWishList(@PathVariable Long productId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "12") int size,
                                                             @AuthenticationPrincipal UserDetails userDetails){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(wishListService.toggleWishList(userDetails,productId,pageable), HttpStatus.OK);
    }

}
