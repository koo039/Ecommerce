package org.dd.bre.Repo;

import org.dd.bre.model.Category;
import org.dd.bre.model.Order;
import org.dd.bre.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    @Query("""
            SELECT p FROM Product p
            LEFT JOIN FETCH p.productVariants pv
            left join fetch pv.images
            WHERE p.productName = :productName
            """)
    Product findByProductName(String productName);

    @Query("""
       SELECT DISTINCT p.id
       FROM Product p
       JOIN p.wishLists w
       WHERE w.id IN :ids
       """)
    Page<Long> findAllByWishLists_IdIn(List<Long> ids, Pageable pageable);

    @Query("SELECT p.id FROM Product p")
    Page<Long> findAllProducts(Pageable pageable);

    @Query("SELECT p.id FROM Product p JOIN p.reviews r GROUP BY p HAVING AVG(r.rate) >= 4")
    Page<Long> findAllByHighRate(Pageable pageable);

    @EntityGraph(attributePaths = {
            "productVariants",
            "productVariants.images"
    })
    List<Product> findAllByIdIn(List<Long> ids);

    @Query("SELECT p.id FROM Product p JOIN p.category c join p.reviews r WHERE c = :category GROUP BY p HAVING AVG(r.rate) >= 4")
    Page<Long> findAllByCategoryHighRate(@Param("category")Category category, Pageable pageable);

    @Query("SELECT p.id FROM Product p WHERE p.category = :category")
    Page<Long> findAllByCategory(Category category, Pageable pageable);
}
