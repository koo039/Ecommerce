package org.dd.bre.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddItemToCartDto {

    @NotNull
    Long variantId;

    @Min(1)
    Integer quantity;
}
