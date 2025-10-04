package org.dd.bre.Repo;

import org.dd.bre.model.Category;
import org.dd.bre.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findByProductName(String productName);

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByWishLists_IdIn(List<Long> ids);

    @Query("select p from Product p order by p.price asc")
    List<Product> findAllAsc();

    @Query("select p from Product p order by p.price desc")
    List<Product> findAllDesc();

    @Query("SELECT p FROM Product p JOIN p.reviews r GROUP BY p HAVING AVG(r.rate) >= 4")
    List<Product> findAllByHighRate();

}
