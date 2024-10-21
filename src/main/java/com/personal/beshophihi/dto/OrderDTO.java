package com.personal.beshophihi.dto;

import com.personal.beshophihi.utils.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String note;
    private String paymentMethod;
    private Long deliveryAddressID;
    private BigDecimal totalPrice;
    private Date updateDate;
    private Long statusId;
    private Long userId;
    private List<OrderDetailDTO> orderDetails = new ArrayList<>();
}

