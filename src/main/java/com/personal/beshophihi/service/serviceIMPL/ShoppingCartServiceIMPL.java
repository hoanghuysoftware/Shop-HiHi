package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.ShoppingCartDetailDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.model.Product;
import com.personal.beshophihi.model.ShoppingCart;
import com.personal.beshophihi.model.ShoppingCartDetail;
import com.personal.beshophihi.repository.ProductRepo;
import com.personal.beshophihi.repository.ShoppingCartRepo;
import com.personal.beshophihi.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceIMPL implements ShoppingCartService {
    private final ShoppingCartRepo shoppingCartRepo;
    private final ProductRepo productRepo;


    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found cart with id: "+ id)
        );
    }

    @Override
    public ShoppingCart getShoppingCartByUserId(Long userId) {
        return shoppingCartRepo.getShoppingCartByUserId(userId).orElseThrow(
                () -> new EntityNotFound("Not found cart with user id: "+ userId)
        );
    }

    @Override
    @Transactional
    public ShoppingCart addProductToCart(Long idCart, ShoppingCartDetailDTO shoppingCartDetailDTO) {
        ShoppingCart shoppingCart = shoppingCartRepo.findById(idCart).orElseThrow(
                () -> new EntityNotFound("Not found cart with id: "+ idCart)
        );
        Product product = productRepo.findById(shoppingCartDetailDTO.getIdProduct()).orElseThrow(
                () -> new EntityNotFound("Not found product with id: "+ shoppingCartDetailDTO.getIdProduct())
        );
        BigDecimal totalPrice = BigDecimal.valueOf(shoppingCartDetailDTO.getQuantity())
                .multiply(product.getSalePrice());

//        Update totalPrice and totalQuantity of Cart
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice().add(totalPrice));
        shoppingCart.setTotalQuantity(shoppingCart.getTotalQuantity()+ shoppingCartDetailDTO.getQuantity());

        List<ShoppingCartDetail> shoppingCartDetailList = new ArrayList<>();
        ShoppingCartDetail shoppingCartDetail = ShoppingCartDetail.builder()
                .shoppingCart(shoppingCart)
                .product(product)
                .unitPrice(product.getSalePrice())
                .totalPrice(totalPrice)
                .quantity(shoppingCartDetailDTO.getQuantity())
                .build();
        shoppingCartDetailList.add(shoppingCartDetail);
        shoppingCart.setShoppingCartDetails(shoppingCartDetailList);
        System.out.println(shoppingCart);
        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    public ShoppingCart updateQuantityProductInCart(Long idCart, ShoppingCartDetailDTO shoppingCartDetailDTO) {
//        ShoppingCart shoppingCart = shoppingCartRepo.findById(idCart).orElseThrow(
//                () -> new EntityNotFound("Not found cart with id: "+ idCart)
//        );
//
////        Update totalPrice and totalQuantity of Cart
//        shoppingCart.setTotalQuantity(shoppingCart.getTotalQuantity()+ shoppingCartDetailDTO.getQuantity());
        return null;
    }

    @Override
    public void removeProductFromCart(Long idCart, ShoppingCartDetailDTO shoppingCartDetailDTO) {

    }
}
