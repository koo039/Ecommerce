package org.dd.bre.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderSummary {
    BigDecimal subtotal;
    BigDecimal tax;
    BigDecimal shipping;
    BigDecimal total;
}
