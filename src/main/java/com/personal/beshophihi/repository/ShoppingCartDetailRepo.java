package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.ShoppingCartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartDetailRepo extends JpaRepository<ShoppingCartDetail, Long> {
}
