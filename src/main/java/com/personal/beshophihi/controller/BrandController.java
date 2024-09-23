package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.BrandDTO;
import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAllBrands() {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched all brands successfully !")
                        .data(brandService.getAllBrands())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetBrandById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched brand with ID: " + id + " successfully.")
                        .data(brandService.getBrandById(id))
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> doCreateBrand(@RequestBody BrandDTO brandDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Brand created successfully.")
                        .data(brandService.createBrand(brandDTO))
                        .build(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> doUpdateBrand(@PathVariable Long id, @RequestBody BrandDTO brandDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Brand with ID: " + id + " updated successfully.")
                        .data(brandService.updateBrand(id, brandDTO))
                        .build(),
                HttpStatus.OK);
    }

    @PatchMapping("/active")
    public ResponseEntity<ResponseMessage> doUpdateBrandActive(@RequestParam Long id, @RequestParam Boolean active) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Brand with ID: " + id + " active updated successfully.")
                        .data(brandService.updateActive(id, active))
                        .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> doDeleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Brand with ID: " + id + " deleted successfully.")
                        .data("")
                        .build(),
                HttpStatus.OK);
    }



}
