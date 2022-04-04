package com.example.webshop.facade.impl;

import com.example.webshop.dto.BrandDTO;
import com.example.webshop.entity.Brand;
import com.example.webshop.facade.BrandFacade;
import com.example.webshop.form.BrandForm;
import com.example.webshop.service.BrandService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BrandFacadeImpl implements BrandFacade {

    private final BrandService brandService;

    public BrandFacadeImpl(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public List<BrandDTO> getAll() {
        return brandService.getAll()
                .stream()
                .map(this::brandDtoMapper)
                .toList();
    }

    @Override
    public Optional<BrandDTO> getById(Long id) {
        return brandService.getById(id).map(this::brandDtoMapper);
    }

    @Override
    public Optional<BrandDTO> save(BrandForm brandForm) {
       return brandService.save(toBrand(brandForm)).map(this::brandDtoMapper);
    }

    private BrandDTO brandDtoMapper(Brand brand) {
        return new BrandDTO(brand.getName());
    }

    private Brand toBrand(BrandForm brandForm) {
        return new Brand(brandForm.getName());
    }

}
