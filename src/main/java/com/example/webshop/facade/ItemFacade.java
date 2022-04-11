package com.example.webshop.facade;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.form.ItemForm;

import java.util.List;
import java.util.Optional;

public interface ItemFacade {

    List<ItemDto> getAll();

    Optional<ItemDto> getById(Long id);

    Optional<ItemDto> save(ItemForm itemForm);

    Optional<ItemDto> deleteById(Long id);

}
