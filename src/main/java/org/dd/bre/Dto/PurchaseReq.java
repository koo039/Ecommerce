package org.dd.bre.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseReq{
    @NotNull
    Long addressId;
    @NotBlank
    String paymentMethod;
    @NotEmpty @Valid
    List<PurchaseItem> items;

}
