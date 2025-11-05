package org.dd.bre.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseReq{
    @NotBlank
    AddressRequest address;
    @NotBlank
    String paymentMethod;
    @NotBlank
    List<PurchaseItem> items;

}
