package com.example.webshop.facade;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.dto.PageDto;
import com.example.webshop.specification.PageSettings;
import com.example.webshop.form.ItemFilterForm;
import com.example.webshop.form.ItemForm;

import java.util.List;
import java.util.Optional;

public interface ItemFacade {

    PageDto<ItemDto> getAll(PageSettings pageSettings, ItemFilterForm itemFilterForm);

    Optional<ItemDto> getById(Long id);

    Optional<ItemDto> save(ItemForm itemForm);

    Optional<ItemDto> deleteById(Long id);

}
