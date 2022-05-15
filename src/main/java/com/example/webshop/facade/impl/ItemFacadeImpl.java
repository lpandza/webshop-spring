package com.example.webshop.facade.impl;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.dto.PageDto;
import com.example.webshop.entity.Brand;
import com.example.webshop.entity.Item;
import com.example.webshop.mapper.PageDtoMapper;
import com.example.webshop.specification.ItemPageSettings;
import com.example.webshop.facade.ItemFacade;
import com.example.webshop.form.ItemFilterForm;
import com.example.webshop.form.ItemForm;
import com.example.webshop.service.BrandService;
import com.example.webshop.service.ItemService;
import com.example.webshop.specification.ItemSpecifications;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ItemFacadeImpl implements ItemFacade {
    private final int PAGE_OFFSET = 1;

    private final ItemService itemService;
    private final BrandService brandService;
    private final PageDtoMapper pageDtoMapper;

    public ItemFacadeImpl(ItemService itemService, BrandService brandService, PageDtoMapper pageDtoMapper) {
        this.itemService = itemService;
        this.brandService = brandService;
        this.pageDtoMapper = pageDtoMapper;
    }

    @Override
    public PageDto<ItemDto> getAll(ItemPageSettings itemPageSettings, ItemFilterForm itemFilterForm) {
        Sort sort = itemPageSettings.buildSort();
        Pageable pageable = PageRequest.of(itemPageSettings.getPage() - PAGE_OFFSET, itemPageSettings.getItemsPerPage(), sort);

        Specification<Item> itemSpecification = getItemSpecification(itemFilterForm);

        return pageDtoMapper.map(itemService.getAll(pageable, itemSpecification).map(this::toItemDto));
    }

    @Override
    public Optional<ItemDto> getById(Long id) {
        return itemService.getById(id).map(this::toItemDto);
    }

    @Override
    public Optional<ItemDto> save(ItemForm itemForm) {
        Optional<Brand> brandOptional = brandService.getById(itemForm.getBrand().getId());

        if (brandOptional.isEmpty()) return Optional.empty();

        return itemService.save(toItem(itemForm, brandOptional.get())).map(this::toItemDto);
    }

    @Override
    public Optional<ItemDto> deleteById(Long id) {
        Optional<Item> itemOptional = itemService.getById(id);
        itemOptional.ifPresent(i -> itemService.deleteById(i.getId()));

        return itemOptional.map(this::toItemDto);
    }

    @Override
    public Optional<ItemDto> update(Long id, ItemForm itemForm) {
        Optional<Brand> brandOptional = brandService.getById(itemForm.getBrand().getId());
        Optional<Item> itemOptional = itemService.getById(id);

        if (brandOptional.isEmpty() || itemOptional.isEmpty()) return Optional.empty();

//        return itemService.update(id, toItem(itemForm, brandOptional.get())).map(this::toItemDto);
        Item newItem = toItem(itemForm, brandOptional.get());
        return itemService.getById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setDescription(newItem.getDescription());
                    item.setPrice(newItem.getPrice());
                    item.setQuantity(newItem.getQuantity());
                    item.setBrand(newItem.getBrand());
                    return itemService.save(item).map(this::toItemDto);
                })
                .orElseGet(Optional::empty);
    }

    private Specification<Item> getItemSpecification(ItemFilterForm itemFilterForm) {
        return Specification
                .where(ItemSpecifications.priceGt(itemFilterForm))
                .and(ItemSpecifications.priceLt(itemFilterForm))
                .and(ItemSpecifications.containsBrand(itemFilterForm));
    }

    private ItemDto toItemDto(Item item){
        return new ItemDto(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getQuantity(), item.getBrand());
    }

    private Item toItem(ItemForm itemForm, Brand brand){
        return new Item(itemForm.getName(), itemForm.getDescription(), itemForm.getPrice(), itemForm.getQuantity(), brand);
    }
}
