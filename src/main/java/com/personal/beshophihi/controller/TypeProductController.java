package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.dto.TypeProductDTO;
import com.personal.beshophihi.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/type-product")
@RequiredArgsConstructor
public class TypeProductController {
    private final ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAllTypeProducts() {
        return new ResponseEntity<>(ResponseMessage.builder()
                .status("TRUE")
                .message("Fetched all type of product successfully !")
                .data(productTypeService.getAllProductType())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetTypeProductById(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched type of product with ID: " + id + " successfully.")
                        .data(productTypeService.getProductTypeById(id))
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> doCreateTypeProduct(@RequestBody TypeProductDTO typeProductDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Type of product created successfully.")
                        .data(productTypeService.createProductType(typeProductDTO))
                        .build(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> doUpdateTypeProduct(@PathVariable Long id, @RequestBody TypeProductDTO typeProductDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Type of product with ID: " + id + " updated successfully.")
                        .data(productTypeService.updateProductType(id, typeProductDTO))
                        .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> doDeleteTypeProduct(@PathVariable Long id) {
        productTypeService.deleteProductType(id);
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Type of product with ID: " + id + " deleted successfully.")
                        .data("")
                        .build(),
                HttpStatus.OK);
    }

    @PatchMapping("/active")
    public ResponseEntity<ResponseMessage> doUpdateActiveTypeProduct(@RequestParam("id") Long id,
                                                                     @RequestParam("active") Boolean active) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Type of product update active with ID: " + id + " successfully.")
                        .data(productTypeService.updateActiveTypeProduct(id, active))
                        .build(),
                HttpStatus.OK);
    }



}
