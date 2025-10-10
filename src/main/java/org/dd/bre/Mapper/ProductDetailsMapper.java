package org.dd.bre.Mapper;

import org.dd.bre.Dto.*;
import org.dd.bre.model.Image;
import org.dd.bre.model.Product;
import org.dd.bre.model.ProductVariant;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class ProductDetailsMapper {

    public ProductDetailsDto mapToProductDTO(Product product) {
        return new ProductDetailsDto(
                product.getId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getOriginalPrice(),
                product.getProductVariants().stream()
                        .map(this::mapToProductVariantDTO)
                        .collect(Collectors.toList())

        );
    }

    public ProductVariantDetailsDto mapToProductVariantDTO(ProductVariant productVariant) {


        return new ProductVariantDetailsDto(
                productVariant.getId(),
                productVariant.getSku(),
                productVariant.getSize(),
                productVariant.getColor(),
                productVariant.getStockQty(),
                productVariant.getImages().stream()
                        .map(this::mapToImage)
                        .collect(Collectors.toList())

        );
    }
    public ImageDto mapToImage(Image image) {
        return new ImageDto(
                image.getId(),
                image.getUrl()
        );
    }
}
