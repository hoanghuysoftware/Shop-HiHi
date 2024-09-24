package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {
    boolean existsByName(String name);
}
