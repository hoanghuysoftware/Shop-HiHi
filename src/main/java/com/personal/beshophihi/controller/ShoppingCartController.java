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


}