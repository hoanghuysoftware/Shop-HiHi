package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.model.Review;
import com.personal.beshophihi.model.ReviewDTO;
import com.personal.beshophihi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<ResponseMessage> doGetAllReviewsByProductId(@RequestParam("product-id") Long id
    ) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched all reviews by product id: " + id + " successfully.")
                        .data(reviewService.getReviewsByProductId(id))
                        .build(),
                HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseMessage> doGetProductReviewsByOrderId(@PathVariable Long orderId) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Fetched all reviews by product id: " + orderId + " successfully.")
                        .data(reviewService.getProductExistReviewByOrderId(orderId))
                        .build(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> doCreateReview(@RequestBody ReviewDTO reviewDTO) {
        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .status("TRUE")
                        .message("Review created successfully.")
                        .data(reviewService.createNewReview(reviewDTO))
                        .build(),
                HttpStatus.CREATED);
    }


}
