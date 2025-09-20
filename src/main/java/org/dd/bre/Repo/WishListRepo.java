package org.dd.bre.Repo;

import org.dd.bre.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepo extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserId(Integer userId);

    WishList findByProductIdAndUserId(Integer productId, Integer userId);
}
