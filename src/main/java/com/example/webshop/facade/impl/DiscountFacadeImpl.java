package com.example.webshop.facade.impl;

import com.example.webshop.dto.DiscountDto;
import com.example.webshop.entity.Discount;
import com.example.webshop.facade.DiscountFacade;
import com.example.webshop.form.DiscountForm;
import com.example.webshop.service.DiscountService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DiscountFacadeImpl implements DiscountFacade {

    private final DiscountService discountService;

    public DiscountFacadeImpl(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public Optional<DiscountDto> getById(Long id) {
        return discountService.getById(id).map(this::toDiscountDto);
    }

    @Override
    public Optional<DiscountDto> getByCode(String code) {
        return discountService.getByCode(code).map(this::toDiscountDto);
    }

    @Override
    public Optional<DiscountDto> save(DiscountForm discountForm) {
        return discountService.save(toDiscount(discountForm)).map(this::toDiscountDto);
    }

    private DiscountDto toDiscountDto(Discount discount){
        return new DiscountDto
                (discount.getId(), discount.getCode(), discount.getDiscountPercentage(), discount.getIsUsed());
    }

    private Discount toDiscount(DiscountForm discountForm){
        return new Discount(discountForm.getCode(), discountForm.getDiscountPercentage(), discountForm.getIsUsed());
    }
}
