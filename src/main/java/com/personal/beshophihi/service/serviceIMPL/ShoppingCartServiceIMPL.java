package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.ShoppingCartDetailDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.model.Product;
import com.personal.beshophihi.model.ShoppingCart;
import com.personal.beshophihi.model.ShoppingCartDetail;
import com.personal.beshophihi.repository.ProductRepo;
import com.personal.beshophihi.repository.ShoppingCartDetailRepo;
import com.personal.beshophihi.repository.ShoppingCartRepo;
import com.personal.beshophihi.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceIMPL implements ShoppingCartService {
    private final ShoppingCartRepo shoppingCartRepo;
    private final ProductRepo productRepo;
    private final ShoppingCartDetailRepo shoppingCartDetailRepo;


    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        return shoppingCartRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found cart with id: " + id)
        );
    }

    @Override
    public ShoppingCart getShoppingCartByUserId(Long userId) {
        return shoppingCartRepo.getShoppingCartByUserId(userId).orElseThrow(
                () -> new EntityNotFound("Not found cart with user id: " + userId)
        );
    }

    private ShoppingCart findShoppingCart(Long id) {
        return shoppingCartRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found cart with id: " + id)
        );
    }

    private Product findProduct(Long id) {
        return productRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found product with id: " + id)
        );
    }

    @Override
    @Transactional
    public ShoppingCart addProductToCart(Long idCart, ShoppingCartDetailDTO shoppingCartDetailDTO) {
        ShoppingCart shoppingCart = findShoppingCart(idCart);
        Product product = findProduct(shoppingCartDetailDTO.getIdProduct());
        List<ShoppingCartDetail> shoppingCartDetailList = shoppingCart.getShoppingCartDetails();
        BigDecimal totalPrice = BigDecimal.valueOf(shoppingCartDetailDTO.getQuantity())
                .multiply(product.getSalePrice());

//       Check product existed in cart
//       Handle when product existed in cart
        boolean productExists = false;
        for (ShoppingCartDetail item : shoppingCartDetailList) {
            if (item.getProduct().getId().equals(product.getId())) {
                shoppingCartDetailDTO.setQuantity(item.getQuantity() + 1);
                productExists = true;
                break;
            }
        }
//        Handle when product not exists in cart
//        Update totalPrice and totalQuantity of Cart
        if (!productExists) {
            shoppingCart.setTotalPrice(shoppingCart.getTotalPrice().add(totalPrice));
            shoppingCart.setTotalQuantity(shoppingCart.getTotalQuantity() + shoppingCartDetailDTO.getQuantity());

            ShoppingCartDetail shoppingCartDetail = ShoppingCartDetail.builder()
                    .shoppingCart(shoppingCart)
                    .product(product)
                    .unitPrice(product.getSalePrice())
                    .totalPrice(totalPrice)
                    .quantity(shoppingCartDetailDTO.getQuantity())
                    .build();
            shoppingCartDetailList.add(shoppingCartDetail);
            shoppingCart.setShoppingCartDetails(shoppingCartDetailList);
            return shoppingCartRepo.save(shoppingCart);
        } else {
            return updateQuantityProductInCart(idCart, shoppingCartDetailDTO);
        }
    }

    @Override
    public ShoppingCart updateQuantityProductInCart(Long idCart, ShoppingCartDetailDTO shoppingCartDetailDTO) {
        Product product = findProduct(shoppingCartDetailDTO.getIdProduct());
        ShoppingCart shoppingCart = findShoppingCart(idCart);
        BigDecimal totalPrice = BigDecimal.valueOf(shoppingCartDetailDTO.getQuantity())
                .multiply(product.getSalePrice());
        List<ShoppingCartDetail> shoppingCartDetailList = shoppingCart.getShoppingCartDetails();

//       Check product existed in cart
//       Handle when product existed in cart
        for (ShoppingCartDetail item : shoppingCartDetailList) {
            if (item.getProduct().getId().equals(product.getId())) {
                // Loai bo gia va sl cua product trong gio hang
                shoppingCart.setTotalPrice(shoppingCart.getTotalPrice().subtract(item.getTotalPrice()));
                shoppingCart.setTotalQuantity(shoppingCart.getTotalQuantity() - item.getQuantity());

                // Cap nhat so luong va tong gia cua san pham trong detail
                item.setQuantity(shoppingCartDetailDTO.getQuantity());
                item.setTotalPrice(totalPrice);

                // Cap nhat gia va so luong gio hang
                shoppingCart.setTotalPrice(shoppingCart.getTotalPrice().add(totalPrice));
                shoppingCart.setTotalQuantity(shoppingCart.getTotalQuantity() + shoppingCartDetailDTO.getQuantity());
                break;
            }
        }
        return shoppingCartRepo.save(shoppingCart);
    }

    @Override
    @Transactional
    public boolean removeProductFromCart(Long idCart, Long idProduct) {
        ShoppingCart shoppingCart = findShoppingCart(idCart);

        ShoppingCartDetail shoppingCartDetail = shoppingCartDetailRepo
                .getShoppingCartDetailByProductIdAndAndShoppingCartId(idProduct, idCart);
        if(shoppingCartDetail == null){
            throw new EntityNotFound("Not found product with id: "+idProduct + " in cart !");
        }
        shoppingCart.setTotalQuantity(shoppingCart.getTotalQuantity() - shoppingCartDetail.getQuantity());
        shoppingCart.setTotalPrice(shoppingCart.getTotalPrice().subtract(shoppingCartDetail.getTotalPrice()));

        int check = shoppingCartDetailRepo.deleteShoppingCartDetailByProductIdAndShoppingCartId(idProduct, idCart);
        shoppingCartRepo.save(shoppingCart);
        return check != 0;
    }


}
