package org.dd.bre.Repo;

import org.dd.bre.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    CartItem findByIdAndCart_User_Id(Long id,Long userId);

    long countByCartId(Long cartId);

    CartItem findByCartIdAndProductVariantId(Long cartId, Long productVariantId);
}
