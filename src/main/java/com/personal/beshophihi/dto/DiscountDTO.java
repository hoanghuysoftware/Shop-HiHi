package com.personal.beshophihi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO {
    private String name;
    private int amount;

    @JsonProperty("start-date")
    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date startDate;

    @JsonFormat(pattern = "MM-dd-yyyy")
    @JsonProperty("end-date")
    private Date endDate;
}
