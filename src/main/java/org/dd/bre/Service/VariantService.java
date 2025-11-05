package org.dd.bre.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.dd.bre.Dto.PurchaseItem;
import org.dd.bre.Exception.ProductVariantNotFoundException;
import org.dd.bre.Repo.ProductVariantRepo;
import org.dd.bre.model.ProductVariant;
import org.dd.bre.model.VariantStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VariantService {
    private final ProductVariantRepo productVariantRepo;

    @Transactional
    public void decrementQuantityOfProductVariant(List<PurchaseItem> purchaseItem){
        for(PurchaseItem item : purchaseItem){
            ProductVariant productVariant = productVariantRepo.findById(item.getVariantId()).orElseThrow(()->new ProductVariantNotFoundException("product variant not found"));

            if(productVariant.getStatus().equals(VariantStatus.OUTOFSTOCK)){
                throw new IllegalStateException("product variant status is out-of-stock");
            }
            int stockQty = productVariant.getStockQty() - item.getQuantity();

            if(stockQty <= 0){
                productVariant.setStockQty(0);
                productVariant.setStatus(VariantStatus.OUTOFSTOCK);
            }
            else
                productVariant.setStockQty(stockQty);

            productVariantRepo.save(productVariant);
        }
    }
}
