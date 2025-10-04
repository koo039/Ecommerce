package org.dd.bre.Dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class UpdateItemQuantityDto {

    @Min(1)
    private Integer quantity;
}
