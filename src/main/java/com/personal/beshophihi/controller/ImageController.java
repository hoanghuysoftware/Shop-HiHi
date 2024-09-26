package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.ImageDTO;
import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageController {
    private final ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> doGet(@PathVariable Long id) {
        return new ResponseEntity<>(ResponseMessage.builder()
                .status("TRUE")
                .message("get all message by id product successfully !")
                .data(imageService.getImagesByIdProduct(id))
                .build(), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<ResponseMessage> doPost(@PathVariable Long id,
                                                  @RequestPart List<MultipartFile> files) {
        return new ResponseEntity<>(ResponseMessage.builder()
                .status("TRUE")
                .message("add images by id product successfully !")
                .data(imageService.createImagesByIdProduct(id, files))
                .build(), HttpStatus.OK);
    }
}
