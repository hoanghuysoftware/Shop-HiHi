package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.DiscountDTO;
import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/discount")
public class DiscountController {
    private final DiscountService discountService;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAllDiscounts() {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched all discounts successfully.")
                        .data(discountService.getAllDiscount())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetDiscountById(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched discount with ID: " + id + " successfully.")
                        .data(discountService.getDiscountById(id))
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> doCreateDiscount(@RequestBody DiscountDTO discountDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Discount created successfully.")
                        .data(discountService.createNewDiscount(discountDTO))
                        .build(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> doUpdateDiscount(@PathVariable Long id, @RequestBody DiscountDTO discountDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Discount with ID: " + id + " updated successfully.")
                        .data(discountService.updateDiscountById(id, discountDTO))
                        .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> doDeleteDiscount(@PathVariable Long id) {
        discountService.deleteDiscountById(id);
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Discount with ID: " + id + " deleted successfully.")
                        .data("")
                        .build(),
                HttpStatus.OK);
    }





}
