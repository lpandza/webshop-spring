package com.example.webshop.service.impl;

import com.example.webshop.entity.Discount;
import com.example.webshop.repository.DiscountRepository;
import com.example.webshop.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public Optional<Discount> getById(Long id) {
        return discountRepository.findById(id);
    }

    @Override
    public Optional<Discount> getByCode(String code) {
        return discountRepository.findDiscountByCode(code);
    }

    @Override
    public Optional<Discount> save(Discount discount) {
        return Optional.of(discountRepository.save(discount));
    }
}
