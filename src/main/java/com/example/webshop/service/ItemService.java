package com.example.webshop.service;

import com.example.webshop.entity.Brand;
import com.example.webshop.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> getAll();

    Optional<Item> getById(Long id);

    Optional<Item> save(Item item);

    void deleteById(Long id);
}
