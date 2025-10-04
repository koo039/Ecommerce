package org.dd.bre.Repo;

import org.dd.bre.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    Category findByCategoryName(String categoryName);

}

