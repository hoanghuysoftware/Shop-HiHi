package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.DiscountDTO;
import com.personal.beshophihi.model.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> getAllDiscount();
    Discount getDiscountById(Long id);
    Discount getDiscountByName(String name);
    Discount createNewDiscount(DiscountDTO discountDTO);
    Discount updateDiscountById(Long id, DiscountDTO discountDTO);
    void deleteDiscountById(Long id);
}
