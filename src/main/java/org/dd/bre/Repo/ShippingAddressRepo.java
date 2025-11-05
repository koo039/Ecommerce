package org.dd.bre.Repo;

import org.dd.bre.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingAddressRepo extends JpaRepository<ShippingAddress, Long> {
    @Query("""
        SELECT a FROM ShippingAddress a
        WHERE LOWER(TRIM(a.addressLine1)) = LOWER(TRIM(:addressLine1))
          AND LOWER(TRIM(COALESCE(a.addressLine2, ''))) = LOWER(TRIM(COALESCE(:addressLine2, '')))
          AND LOWER(TRIM(a.city)) = LOWER(TRIM(:city))
          AND LOWER(TRIM(a.state)) = LOWER(TRIM(:state))
          AND LOWER(TRIM(a.postalCode)) = LOWER(TRIM(:postalCode))
          AND LOWER(TRIM(a.country)) = LOWER(TRIM(:country))
          AND LOWER(TRIM(a.label)) = LOWER(TRIM(:label))
          AND LOWER(TRIM(a.phoneNumber)) = LOWER(TRIM(:phoneNumber))
    """)
    Optional<ShippingAddress> findMatchingAddress(
            @Param("addressLine1") String addressLine1,
            @Param("addressLine2") String addressLine2,
            @Param("city") String city,
            @Param("state") String state,
            @Param("postalCode") String postalCode,
            @Param("country") String country,
            @Param("label") String label,
            @Param("phoneNumber") String phoneNumber
    );
}
