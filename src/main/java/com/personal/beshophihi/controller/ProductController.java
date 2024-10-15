package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/not-active")
    public ResponseEntity<ResponseMessage> doGetAllNotActive () {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .code(HttpStatus.OK.value())
                        .message("Get all product successfully !")
                        .data(productService.getAllProductNotActive())
                        .build(),
                HttpStatus.OK);
    }
    @GetMapping("/active")
    public ResponseEntity<ResponseMessage> doGetAllActive () {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .code(HttpStatus.OK.value())
                        .message("Get all product successfully !")
                        .data(productService.getAllProductActive())
                        .build(),
                HttpStatus.OK);
    }
    @GetMapping("/not-sale")
    public ResponseEntity<ResponseMessage> doGetAllNotSale () {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .code(HttpStatus.OK.value())
                        .message("Get all product successfully !")
                        .data(productService.getAllProductActiveFalse())
                        .build(),
                HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetById (@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .code(HttpStatus.OK.value())
                        .message("Get product by id successfully !")
                        .data(productService.getProductById(id))
                        .build(),
                HttpStatus.OK);
    }
}
