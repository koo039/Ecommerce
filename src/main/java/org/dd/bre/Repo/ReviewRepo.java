package org.dd.bre.Repo;

import org.dd.bre.model.Product;
import org.dd.bre.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    Integer countReviewsByProduct(Product product);
    @Query("SELECT AVG(r.rate) FROM Review r WHERE r.product = :product" )
    Double findAverageRatingByProduct(Product product);

}
