package com.example.webshop.facade;

import com.example.webshop.dto.BrandDTO;
import com.example.webshop.entity.Brand;
import com.example.webshop.form.BrandForm;

import java.util.List;
import java.util.Optional;

public interface BrandFacade {

    List<BrandDTO> getAll();

    Optional<BrandDTO> getById(Long id);

    Optional<BrandDTO> save(BrandForm brandForm);
}
