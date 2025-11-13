package org.dd.bre.Mapper;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.ProductDTO;
import org.dd.bre.Repo.ReviewRepo;
import org.dd.bre.model.Image;
import org.dd.bre.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ReviewRepo reviewRepo;

    public ProductDTO mapToProductDTO(Product product) {

        String imageUrl = product.getProductVariants().stream()
                .flatMap(variant -> new ArrayList<>(variant.getImages()).stream())
                .map(Image::getUrl)
                .findFirst()
                .orElse("default-image.png");


        Integer reviewCount = reviewRepo.countReviewsByProduct(product);
        Double avgRating = reviewRepo.findAverageRatingByProduct(product);

        return new ProductDTO(
                product.getId(),
                product.getProductName(),
                imageUrl,
                product.getPrice(),
                product.getOriginalPrice(),
                reviewCount,
                avgRating != null ? avgRating : 0.0
        );

    }
}
