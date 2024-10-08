package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {
    Optional<ShoppingCart> getShoppingCartByUserId(Long userId);
}
