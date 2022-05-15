package com.example.webshop.service;

import com.example.webshop.entity.Discount;

import java.util.Optional;

public interface DiscountService {

    Optional<Discount> getById(Long id);

    Optional<Discount> getByCode(String code);

    Optional<Discount> save(Discount discount);

}
