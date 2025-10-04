package org.dd.bre.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductDetailsDto {
    private Long id;
    private String  productName;
    private String  productDescription;
    private BigDecimal productPrice;
    private BigDecimal originalPrice;
    private List<ProductVariantDetailsDto> productVariantList;
}
