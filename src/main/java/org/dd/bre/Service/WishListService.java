package org.dd.bre.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.ProductPageResponse;
import org.dd.bre.Exception.ProductNotFoundException;
import org.dd.bre.Exception.UserNotFoundException;
import org.dd.bre.Repo.ProductRepo;
import org.dd.bre.Repo.UserRepo;
import org.dd.bre.Repo.WishListRepo;
import org.dd.bre.model.Product;
import org.dd.bre.Dto.ProductDTO;
import org.dd.bre.model.User;
import org.dd.bre.model.WishList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepo wishListRepo;
    private final ProductService productService;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    public ProductPageResponse getWishList(Long userId, Pageable pageable) {
        List<WishList> wishLists = wishListRepo.findAllByUserId(userId);
        return productService.getAllProductsByWishList(wishLists,pageable);
    }

    @Transactional
    public ProductPageResponse toggleWishList(Long userId, Long productId,Pageable pageable) {

        WishList wishList = wishListRepo.findByProductIdAndUserId(productId,userId);

        if(wishList == null){

            User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
            Product product = productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found"));
            WishList newWishList = new WishList();
            newWishList.setUser(user);
            newWishList.setProduct(product);
            wishListRepo.save(newWishList);
        }
        else {
            wishListRepo.delete(wishList);
        }
        return getWishList(userId,pageable);
    }

}
