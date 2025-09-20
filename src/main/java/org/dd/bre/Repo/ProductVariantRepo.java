package org.dd.bre.Repo;

import org.dd.bre.model.Product;
import org.dd.bre.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProductVariantRepo extends JpaRepository<ProductVariant, Long> {
    @Query("SELECT MIN(r.price) FROM ProductVariant r WHERE r.product = :product")
    BigDecimal findMinPriceByProduct(Product product);
}
