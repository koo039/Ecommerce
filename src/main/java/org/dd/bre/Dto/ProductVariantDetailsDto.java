package org.dd.bre.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductVariantDetailsDto {
    private Long id;
    private String sku;
    private String size;
    private String color;
    private Integer quantity;
    private List<ImageDto> images;
}
