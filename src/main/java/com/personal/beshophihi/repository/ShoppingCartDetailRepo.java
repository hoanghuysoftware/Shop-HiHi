package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.ShoppingCartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartDetailRepo extends JpaRepository<ShoppingCartDetail, Long> {
    List<ShoppingCartDetail> getShoppingCartDetailByShoppingCartId(Long id);


    @Modifying
    @Query("delete from ShoppingCartDetail s where s.product.id = :productId and s.shoppingCart.id = :shoppingCartId")
    int deleteShoppingCartDetailByProductIdAndShoppingCartId(Long productId, Long shoppingCartId);

    ShoppingCartDetail getShoppingCartDetailByProductIdAndAndShoppingCartId(Long productId, Long shoppingCartId);

}
