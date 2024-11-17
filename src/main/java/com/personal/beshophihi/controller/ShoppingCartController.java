package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.dto.ShoppingCartDetailDTO;
import com.personal.beshophihi.model.ShoppingCart;
import com.personal.beshophihi.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetShoppingCartById(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched shopping cart with ID: " + id + " successfully.")
                        .data(shoppingCartService.getShoppingCartById(id))
                        .build(),
                HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseMessage> doGetShoppingCartByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched shopping cart with ID: " + id + " successfully.")
                        .data(shoppingCartService.getShoppingCartByUserId(id))
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ResponseMessage> addProductIntoCart(@PathVariable Long id,
                                                              @RequestBody ShoppingCartDetailDTO shoppingCartDetailDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Shopping cart created successfully.")
                        .data(shoppingCartService.addProductToCart(id, shoppingCartDetailDTO))
                        .build(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateProductIntoCart(@PathVariable Long id,
                                                                 @RequestBody ShoppingCartDetailDTO shoppingCartDetailDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .code(HttpStatus.OK.value())
                        .message("Shopping cart update successfully.")
                        .data(shoppingCartService.updateQuantityProductInCart(id, shoppingCartDetailDTO))
                        .build(),
                HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> doDeleteShoppingCart(@PathVariable Long id,
                                                                @RequestParam("product")Long idProduct) {
        boolean checkDelete = shoppingCartService.removeProductFromCart(id, idProduct);
        if(checkDelete){
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .status("TRUE")
                            .message("Shopping cart with ID: " + id + " deleted successfully.")
                            .build(),
                    HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .status("FAILED")
                            .message("Shopping cart with ID: " + id + " deleted NOT successfully.")
                            .build(),
                    HttpStatus.BAD_REQUEST);
        }
    }



}
