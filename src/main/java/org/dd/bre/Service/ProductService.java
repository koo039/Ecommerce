package org.dd.bre.Service;

import org.dd.bre.Exception.ProductNotFoundException;
import org.dd.bre.Repo.CategoryRepo;
import org.dd.bre.Repo.ProductRepo;
import org.dd.bre.Repo.ReviewRepo;
import org.dd.bre.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ReviewRepo reviewRepo;

    public ProductService(ProductRepo productRepo, CategoryRepo categoryRepo, ReviewRepo reviewRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.reviewRepo = reviewRepo;
    }

    public Product getProductByName(String productName) {
        Product product = productRepo.findByProductName(productName);
        if (product == null) {
            throw new ProductNotFoundException("Product not found: " + productName);
        }
        return product;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepo.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return products.stream()
                .map(this::mapToProductDTO)
                .toList();
    }

    protected List<ProductDTO> getAllProductsByWishList(List<WishList> wishLists) {
        List<Integer> in = wishLists.stream().map(WishList::getId).toList();
        List<Product> products = productRepo.findAllByWishLists_IdIn(in);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found");
        }
        return products.stream()
                .map(this::mapToProductDTO)
                .toList();
    }

    public List<ProductDTO> getAllProductsByCategory(String categoryName) {
        Category category = categoryRepo.findByCategoryName(categoryName);
        if (category == null) {
            throw new ProductNotFoundException("Category not found: " + categoryName);
        }

        List<Product> products = productRepo.findAllByCategory(category);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No products found for category: " + categoryName);
        }
        return products.stream()
                .map(this::mapToProductDTO)
                .toList();
    }


    private ProductDTO mapToProductDTO(Product product) {

        String imageUrl = product.getProductVariants().stream()
                .flatMap(variant -> variant.getImages().stream())
                .map(Image::getUrl)
                .findFirst()
                .orElse("default-image.png");

        BigDecimal price = product.getProductVariants().stream()
                .map(ProductVariant::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        Integer reviewCount = reviewRepo.countReviewsByProduct(product);
        Double avgRating = reviewRepo.findAverageRatingByProduct(product);

        return new ProductDTO(
                product.getProductName(),
                imageUrl,
                price,
                reviewCount,
                avgRating != null ? avgRating : 0.0
        );

    }

}
