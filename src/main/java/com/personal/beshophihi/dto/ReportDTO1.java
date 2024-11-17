package com.personal.beshophihi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDTO1 {
    private Long idProduct;
    private BigDecimal quantity;
}
