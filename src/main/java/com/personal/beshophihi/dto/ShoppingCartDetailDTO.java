package com.personal.beshophihi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDetailDTO {
    private int quantity;
    @JsonProperty("product-id")
    private Long idProduct;
}
