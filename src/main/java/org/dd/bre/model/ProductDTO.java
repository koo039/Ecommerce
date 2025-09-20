package org.dd.bre.model;


import java.math.BigDecimal;

public record ProductDTO(
            String productName,
            String imageUrl,
            BigDecimal price,
            Integer numberOfReviews,
            Double rating
    ) {}
