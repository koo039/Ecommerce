package org.dd.bre.Repo;

import org.dd.bre.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductVariantRepo extends JpaRepository<ProductVariant, Long> {
}
