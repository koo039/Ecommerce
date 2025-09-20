package org.dd.bre.Repo;

import org.dd.bre.model.Category;
import org.dd.bre.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    Product findByProductName(String productName);

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByWishLists_IdIn(List<Integer> ids);

}
