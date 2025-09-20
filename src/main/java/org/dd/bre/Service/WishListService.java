package org.dd.bre.Service;

import org.dd.bre.Exception.ProductNotFoundException;
import org.dd.bre.Exception.UserNotFoundException;
import org.dd.bre.Repo.ProductRepo;
import org.dd.bre.Repo.UserRepo;
import org.dd.bre.Repo.WishListRepo;
import org.dd.bre.model.Product;
import org.dd.bre.model.ProductDTO;
import org.dd.bre.model.User;
import org.dd.bre.model.WishList;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class WishListService {

    private final WishListRepo wishListRepo;
    private final ProductService productService;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    public WishListService(WishListRepo wishListRepo, ProductService productService, UserRepo userRepo, ProductRepo productRepo) {
        this.wishListRepo = wishListRepo;
        this.productService =  productService;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public List<ProductDTO> getWishList(Integer userId) {
        List<WishList> wishLists = wishListRepo.findAllByUserId((userId));
        return productService.getAllProductsByWishList(wishLists);
    }
    public List<ProductDTO> toggleWishList(Integer userId, Integer productId) {

        WishList wishList = wishListRepo.findByProductIdAndUserId(productId,userId);
        User user = userRepo.findById(userId.longValue()).orElseThrow(() -> new UserNotFoundException("User not found"));
        Product product = productRepo.findById(productId.longValue()).orElseThrow(() -> new ProductNotFoundException("Product not found"));
        if(wishList==null){
            WishList newWishList = new WishList();
            newWishList.setCreatedAt(LocalDateTime.now());
            newWishList.setUpdatedAt(LocalDateTime.now());
            newWishList.setUser(user);
            newWishList.setProduct(product);
            crateWishList(newWishList);
        }
        else {
            deleteWishList(wishList);
        }
        return getWishList(userId);
    }
    private void crateWishList(WishList wishList) {
        wishListRepo.save(wishList);
    }
    private void deleteWishList(WishList wishList) {
        wishListRepo.delete(wishList);
    }


}
