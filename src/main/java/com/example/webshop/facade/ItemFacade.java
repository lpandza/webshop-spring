package com.example.webshop.facade;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.dto.PageDto;
import com.example.webshop.entity.Item;
import com.example.webshop.specification.ItemPageSettings;
import com.example.webshop.form.ItemFilterForm;
import com.example.webshop.form.ItemForm;

import java.util.Optional;

public interface ItemFacade {

    PageDto<ItemDto> getAll(ItemPageSettings itemPageSettings, ItemFilterForm itemFilterForm);

    Optional<ItemDto> getById(Long id);

    Optional<ItemDto> save(ItemForm itemForm);

    Optional<ItemDto> deleteById(Long id);

    Optional<ItemDto> update(Long id, ItemForm itemForm);

}
