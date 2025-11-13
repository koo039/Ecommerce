package org.dd.bre.Dto;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductDTO(
            Long id,
            @Schema(description = "Name of the product", example = "suit")
            String productName,
            String imageUrl,
            BigDecimal price,
            BigDecimal originalPrice,
            Integer numberOfReviews,
            Double rating
    ) {}
