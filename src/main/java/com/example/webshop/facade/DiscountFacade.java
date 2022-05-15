package com.example.webshop.facade;

import com.example.webshop.dto.DiscountDto;
import com.example.webshop.entity.Discount;
import com.example.webshop.form.DiscountForm;

import java.util.Optional;

public interface DiscountFacade {

    Optional<DiscountDto> getById(Long id);

    Optional<DiscountDto> getByCode(String code);

    Optional<DiscountDto> save(DiscountForm discountForm);

}
