package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {
    Optional<Discount> findByName(String name);
    boolean existsByName(String name);
}
