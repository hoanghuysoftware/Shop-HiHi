package com.personal.beshophihi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String description;
    private String cpu;
    private String ram;
    private String rom;
    private String card;
    private String screen;

    @JsonProperty("sale-price")
    private BigDecimal salePrice;

    @JsonProperty("available-quantity")
    private int availableQuantity;

    @JsonProperty("brand-id")
    private Long brandID;

    @JsonProperty("discount-id")
    private Long discountID;

    @JsonProperty("type-product-id")
    private List<Long> typeProductIDs;

    @JsonProperty("files-images")
    private List<MultipartFile> filesImages;
}
