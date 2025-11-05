package org.dd.bre.Repo;

import org.dd.bre.model.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepo extends JpaRepository<ProductVariant, Long> {
}
