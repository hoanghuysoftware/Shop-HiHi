package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.ProductDTO;
import com.personal.beshophihi.dto.ProductDTO2;
import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.service.StockReceiptService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stock-receipt")
public class StockReceiptController {
    private final StockReceiptService stockReceiptService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAllStockReceipts() {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched all stock receipts successfully.")
                        .data(stockReceiptService.getAllStockReceipts())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetStockReceiptById(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched stock receipt with ID: " + id + " successfully.")
                        .data(stockReceiptService.getStockReceipt(id))
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseMessage> doCreateStockReceipt(@ModelAttribute ProductDTO productDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Stock receipt created successfully.")
                        .data(productDTO)
                        .build(),
                HttpStatus.CREATED);
    }


}
