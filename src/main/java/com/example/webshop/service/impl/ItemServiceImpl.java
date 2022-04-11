package com.example.webshop.service.impl;

import com.example.webshop.entity.Item;
import com.example.webshop.repository.ItemRepository;
import com.example.webshop.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> getById(Long id) {
        return itemRepository.findById(id);
    }

    @Override
    public Optional<Item> save(Item item) {
        return Optional.of(itemRepository.save(item));
    }

    @Override
    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

}
