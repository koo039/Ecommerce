package org.dd.bre.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseItem {
    @NotBlank
    Long variantId;
    @NotBlank
    Integer quantity;

}
