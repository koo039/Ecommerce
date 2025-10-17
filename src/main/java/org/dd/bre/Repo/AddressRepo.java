package org.dd.bre.Repo;

import org.dd.bre.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address,Long> {
    List<Address> findAllByUserId(Long userId);

    Address findByUserId(Long userId);
}
