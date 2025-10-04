package org.dd.bre.Dto;


import java.math.BigDecimal;

public record ProductDTO(
            Long id,
            String productName,
            String imageUrl,
            BigDecimal price,
            BigDecimal originalPrice,
            Integer numberOfReviews,
            Double rating
    ) {}
