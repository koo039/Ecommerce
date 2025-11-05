package org.dd.bre.Repo;

import org.dd.bre.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
    List<Address> findAllByUserId(Long userId);

    Address findByUserId(Long userId);
}
