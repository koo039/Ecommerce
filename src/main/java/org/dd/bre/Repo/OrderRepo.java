package org.dd.bre.Repo;

import org.dd.bre.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepo extends JpaRepository<Order,Integer> {
    Page<Order> findAllByUserId(Long userId, Pageable pageable);
}
