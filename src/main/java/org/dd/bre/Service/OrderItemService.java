package org.dd.bre.Service;

import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.PurchaseItem;
import org.dd.bre.Exception.ProductVariantNotFoundException;
import org.dd.bre.Repo.ProductVariantRepo;
import org.dd.bre.model.Order;
import org.dd.bre.model.OrderItem;
import org.dd.bre.model.ProductVariant;
import org.dd.bre.model.VariantStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final ProductVariantRepo productVariantRepo;

    protected OrderItem createOrderItem(Order order, PurchaseItem item){
        ProductVariant productVariant = productVariantRepo.findById(item.getVariantId()).orElseThrow(()->new ProductVariantNotFoundException("product variant not found"));

        if(productVariant.getStatus().equals(VariantStatus.OUTOFSTOCK)){
            throw new IllegalStateException("product variant status is out-of-stock");
        }
        if(productVariant.getStockQty() < item.getQuantity()){
            throw new IllegalStateException("product variant quantity is lower than item quantity");
        }
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setPrice(productVariant.getProduct().getPrice());
        orderItem.setQuantity(item.getQuantity());
        orderItem.setProductVariant(productVariant);
        return orderItem;
    }

    protected BigDecimal getSubTotal(List<PurchaseItem> items){
        BigDecimal subTotal = BigDecimal.ZERO;
        for(PurchaseItem item : items){
            ProductVariant productVariant = productVariantRepo.findById(item.getVariantId()).orElseThrow(()->new ProductVariantNotFoundException("product variant not found"));
            subTotal = subTotal.add(productVariant.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())));
        }
        return subTotal;
    }


}
