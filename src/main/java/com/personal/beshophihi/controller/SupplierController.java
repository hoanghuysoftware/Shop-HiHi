package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.dto.SupplierDTO;
import com.personal.beshophihi.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    private final SupplierService supplierService;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAllSuppliers() {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched all suppliers successfully.")
                        .data(supplierService.getAllSuppliers())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetSupplierById(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched supplier with ID: " + id + " successfully.")
                        .data(supplierService.getSupplierById(id))
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> doCreateSupplier(@RequestBody SupplierDTO supplierDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Supplier created successfully.")
                        .data(supplierService.createSupplier(supplierDTO))
                        .build(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> doUpdateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Supplier with ID: " + id + " updated successfully.")
                        .data(supplierService.updateSupplier(id, supplierDTO))
                        .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> doDeleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Supplier with ID: " + id + " deleted successfully.")
                        .data("")
                        .build(),
                HttpStatus.OK);
    }


}
