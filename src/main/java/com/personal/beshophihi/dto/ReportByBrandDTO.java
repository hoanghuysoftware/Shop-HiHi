package com.personal.beshophihi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportByBrandDTO {
    private Long brandId;
    private String brandName;
    private int totalSold;
}
