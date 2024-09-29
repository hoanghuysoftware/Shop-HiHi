package com.personal.beshophihi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private String content;

    @JsonProperty("order-id")
    private Long idOrder;
    @JsonProperty("product-id")
    private Long idProduct;
}
