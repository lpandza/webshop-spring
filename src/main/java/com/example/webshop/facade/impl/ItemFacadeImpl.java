package com.example.webshop.facade.impl;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.entity.Brand;
import com.example.webshop.entity.Item;
import com.example.webshop.facade.ItemFacade;
import com.example.webshop.form.ItemForm;
import com.example.webshop.service.BrandService;
import com.example.webshop.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ItemFacadeImpl implements ItemFacade {

    private final ItemService itemService;
    private final BrandService brandService;

    public ItemFacadeImpl(ItemService itemService, BrandService brandService) {
        this.itemService = itemService;
        this.brandService = brandService;
    }

    @Override
    public List<ItemDto> getAll() {
        return itemService.getAll()
                .stream()
                .map(this::toItemDto)
                .toList();
    }

    @Override
    public Optional<ItemDto> getById(Long id) {
        return itemService.getById(id).map(this::toItemDto);
    }

    @Override
    public Optional<ItemDto> save(ItemForm itemForm) {
        Optional<Brand> brandOptional = brandService.getById(itemForm.getBrandId());

        if (brandOptional.isEmpty()) return Optional.empty();

        return itemService.save(toItem(itemForm, brandOptional.get())).map(this::toItemDto);
    }

    @Override
    public Optional<ItemDto> deleteById(Long id) {
        Optional<Item> itemOptional = itemService.getById(id);
        itemOptional.ifPresent(i -> itemService.deleteById(i.getId()));

        return itemOptional.map(this::toItemDto);
    }

    private ItemDto toItemDto(Item item){
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getQuantity(), item.getBrand());
    }

    private Item toItem(ItemForm itemForm, Brand brand){
        return new Item(itemForm.getName(), itemForm.getDescription(), itemForm.getPrice(), itemForm.getQuantity(), brand);
    }
}
