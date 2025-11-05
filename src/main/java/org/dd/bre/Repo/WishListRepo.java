package org.dd.bre.Repo;

import org.dd.bre.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserId(Long userId);

    WishList findByProductIdAndUserId(Long productId, Long userId);
}
