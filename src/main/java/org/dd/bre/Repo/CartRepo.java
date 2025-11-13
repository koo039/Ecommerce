package org.dd.bre.Repo;


import org.dd.bre.model.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart,Long> {


    @EntityGraph(attributePaths = {
            "items",
            "items.productVariant",
            "items.productVariant.product"
    })
    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    Cart findCartWithItemsAndProductsByUserId(@Param("userId") Long userId);




}
