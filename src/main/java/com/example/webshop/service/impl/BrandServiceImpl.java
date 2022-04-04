package com.example.webshop.service.impl;

import com.example.webshop.entity.Brand;
import com.example.webshop.repository.BrandRepository;
import com.example.webshop.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Optional<Brand> save(Brand brand) {
        return Optional.of(brandRepository.save(brand));
    }
}
