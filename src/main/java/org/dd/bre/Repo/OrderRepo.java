package org.dd.bre.Repo;

import org.dd.bre.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
    Page<Order> findAllByUserId(Long userId, Pageable pageable);
}
