package com.personal.beshophihi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockReceiptDTO {
    @JsonProperty("supplier-id")
    private Long idSupplier;
    private Date receptDate = new Date();
    private List<StockReceiptDetailDTO> stockReceiptDetailDTOS = new ArrayList<>(); // ds chi tiet nhap hang

}
