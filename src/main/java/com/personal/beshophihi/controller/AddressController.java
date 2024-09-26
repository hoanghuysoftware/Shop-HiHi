package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.AddressDTO;
import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address-user")
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAllAddresses() {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched all addresses successfully.")
                        .data(addressService.getAllAddress())
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGetAddressById(@PathVariable Long id) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched address with ID: " + id + " successfully.")
                        .data(addressService.getAddressById(id))
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> doCreateAddress(@RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Address created successfully.")
                        .data(addressService.createAddress(addressDTO))
                        .build(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> doUpdateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Address with ID: " + id + " updated successfully.")
                        .data(addressService.updateAddress(id, addressDTO))
                        .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> doDeleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Address with ID: " + id + " deleted successfully.")
                        .data("")
                        .build(),
                HttpStatus.OK);
    }
}
