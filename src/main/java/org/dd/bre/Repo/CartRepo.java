package org.dd.bre.Repo;


import org.dd.bre.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {
    Cart findByUserId(Long userId);
}
