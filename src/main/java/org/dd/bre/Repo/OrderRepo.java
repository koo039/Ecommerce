package org.dd.bre.Repo;

import org.dd.bre.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    @Query("""
       SELECT o.id
       FROM Order o
       WHERE o.user.id = :userId
       """)
    Page<Long> findPageOfIds(Long userId, Pageable pageable);

    @EntityGraph(attributePaths = {
            "shippingAddress",
            "items",
            "items.productVariant",
            "items.productVariant.product",
            "items.productVariant.images"
    })
    List<Order> findAllByIdIn(List<Long> ids);
}
