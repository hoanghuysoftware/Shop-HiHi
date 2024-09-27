package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.ShoppingCartDetailDTO;
import com.personal.beshophihi.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart getShoppingCartById(Long id);

    ShoppingCart getShoppingCartByUserId(Long userId);

    ShoppingCart addProductToCart(Long idCart, ShoppingCartDetailDTO shoppingCartDetailDTO);

    ShoppingCart updateQuantityProductInCart(Long idCart, ShoppingCartDetailDTO shoppingCartDetailDTO);

    boolean removeProductFromCart(Long idCart, Long idProduct);
}
