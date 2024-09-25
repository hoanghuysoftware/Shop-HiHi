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
public class StockReceiptDetailDTO {
    private int quantity;
    private ProductDTO productDTO;
    private Long productId;

    @JsonProperty("unit-price")
    private BigDecimal unitPrice;

    @JsonProperty("total-price")
    private BigDecimal totalPrice;
}
