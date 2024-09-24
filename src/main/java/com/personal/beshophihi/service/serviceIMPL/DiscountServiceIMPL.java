package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.DiscountDTO;
import com.personal.beshophihi.exception.DateException;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.exception.ExistsEntityException;
import com.personal.beshophihi.exception.InputValidException;
import com.personal.beshophihi.model.Discount;
import com.personal.beshophihi.repository.DiscountRepo;
import com.personal.beshophihi.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceIMPL implements DiscountService {
    private final DiscountRepo discountRepo;

    @Override
    public List<Discount> getAllDiscount() {
        return discountRepo.findAll();
    }

    @Override
    public Discount getDiscountById(Long id) {
        return discountRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found discount by id: " + id)
        );
    }

    @Override
    public Discount getDiscountByName(String name) {
        return discountRepo.findByName(name).orElseThrow(
                () -> new EntityNotFound("Not found discount by name: " + name)
        );
    }

    private void checkBasicException(DiscountDTO discountDTO){
        boolean isExists = discountRepo.existsByName(discountDTO.getName());
        if (isExists) {
            throw new ExistsEntityException("Discount name already exists !");
        }
        Date startDate = discountDTO.getStartDate();
        Date endDate = discountDTO.getEndDate();

        if (endDate.before(startDate)) {
            throw new DateException("End date cannot be before start date");
        }

        if (discountDTO.getAmount() < 0 || discountDTO.getAmount() > 100) {
            throw new InputValidException("Discount amount must be between 0 to 100.");
        }
    }

    @Override
    public Discount createNewDiscount(DiscountDTO discountDTO) {
        checkBasicException(discountDTO);

        Discount newDiscount = Discount.builder()
                .name(discountDTO.getName())
                .amount(discountDTO.getAmount())
                .startDate(discountDTO.getStartDate())
                .endDate(discountDTO.getEndDate())
                .build();

        return discountRepo.save(newDiscount);
    }

    @Override
    public Discount updateDiscountById(Long id, DiscountDTO discountDTO) {
        Discount discount = discountRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found discount by id: " + id)
        );
        checkBasicException(discountDTO);
        discount.setName(discountDTO.getName());
        discount.setAmount(discountDTO.getAmount());
        discount.setStartDate(discountDTO.getStartDate());
        discount.setEndDate(discountDTO.getEndDate());

        return discountRepo.save(discount);
    }

    @Override
    public void deleteDiscountById(Long id) {
        Discount discount = discountRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found discount by id: " + id)
        );
        discountRepo.delete(discount);
    }
}
