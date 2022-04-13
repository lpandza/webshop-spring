package com.example.webshop.facade;

import com.example.webshop.dto.BrandDto;
import com.example.webshop.form.BrandForm;

import java.util.List;
import java.util.Optional;

public interface BrandFacade {

    List<BrandDto> getAll();

    Optional<BrandDto> getById(Long id);

    Optional<BrandDto> save(BrandForm brandForm);

    Optional<BrandDto> deleteById(Long id);
}
