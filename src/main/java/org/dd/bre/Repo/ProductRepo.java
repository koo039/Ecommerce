package org.dd.bre.Repo;

import org.dd.bre.model.Category;
import org.dd.bre.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    Product findByProductName(String productName);

    Page<Product> findAllByWishLists_IdIn(List<Long> ids, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.reviews r GROUP BY p HAVING AVG(r.rate) >= 4")
    Page<Product> findAllByHighRate(Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.category c join p.reviews r WHERE c = :category GROUP BY p HAVING AVG(r.rate) >= 4")
    Page<Product> findAllByCategoryHighRate(@Param("category")Category category, Pageable pageable);

    Page<Product> findAllByCategory(Category category, Pageable pageable);
}
