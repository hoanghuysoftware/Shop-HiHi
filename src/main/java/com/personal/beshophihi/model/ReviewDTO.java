package com.personal.beshophihi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private String content;
    private int rating;

    @JsonProperty("order-id")
    private Long idOrder;
    @JsonProperty("product-id")
    private Long idProduct;
    private String nameCustomer;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateTime;

    public static ReviewDTO convertToDTO(Review review) {
        return ReviewDTO.builder()
                .content(review.getContent())
                .rating(review.getRating())
                .dateTime(review.getDateTime())
                .nameCustomer(review.getOrder().getUser().getName())
                .idOrder(review.getOrder().getId())
                .idProduct(review.getProduct().getId())
                .build();
    }
}
