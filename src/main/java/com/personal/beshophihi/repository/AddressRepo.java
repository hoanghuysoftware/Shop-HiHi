package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {
    boolean existsByNameAddress(String nameAddress);
    List<Address> getAddressesByUserId(Long userId);
}
