package com.example.webshop.facade.impl;

import com.example.webshop.dto.BrandDto;
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
    public List<BrandDto> getAll() {
        return brandService.getAll()
                .stream()
                .map(this::brandDtoMapper)
                .toList();
    }

    @Override
    public Optional<BrandDto> getById(Long id) {
        return brandService.getById(id).map(this::brandDtoMapper);
    }

    @Override
    public Optional<BrandDto> save(BrandForm brandForm) {
       return brandService.save(toBrand(brandForm)).map(this::brandDtoMapper);
    }

    @Override
    public Optional<BrandDto> deleteById(Long id) {
        Optional<Brand> brandOptional = brandService.getById(id);
        brandOptional.ifPresent(b -> brandService.deleteById(b.getId()));

        return brandOptional.map(this::brandDtoMapper);
    }

    private BrandDto brandDtoMapper(Brand brand) {
        return new BrandDto(brand.getId(), brand.getName());
    }

    private Brand toBrand(BrandForm brandForm) {
        return new Brand(brandForm.getName());
    }

}
