package com.example.webshop.service;

import com.example.webshop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Page<Item> getAll(Pageable pageable, Specification<Item> itemSpecification);

    Optional<Item> getById(Long id);

    Optional<Item> save(Item item);

    void deleteById(Long id);
}
