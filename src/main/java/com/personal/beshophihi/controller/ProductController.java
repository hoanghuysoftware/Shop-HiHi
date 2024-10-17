package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/public")
    public ResponseEntity<ResponseMessage> doGetProductPublic(@RequestParam("brand-id") Long idBrand,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size){
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .code(HttpStatus.OK.value())
                        .message("Get all product public successfully !")
                        .totalPage(productService.getProductPublic(page, size, idBrand).getTotalPages())
                        .data(productService.getProductPublic(page, size, idBrand).getContent())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/not-image")
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
