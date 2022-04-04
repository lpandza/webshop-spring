package com.example.webshop.service;

import com.example.webshop.entity.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<Brand> getAll();

    Optional<Brand> getById(Long id);

    Optional<Brand> save(Brand brand);
}
