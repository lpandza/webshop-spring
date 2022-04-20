package com.example.webshop.facade.impl;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.dto.PageDto;
import com.example.webshop.entity.Brand;
import com.example.webshop.entity.Item;
import com.example.webshop.mapper.PageDtoMapper;
import com.example.webshop.specification.PageSettings;
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
    public PageDto<ItemDto> getAll(PageSettings pageSettings, ItemFilterForm itemFilterForm) {
        Sort sort = pageSettings.buildSort();
        Pageable pageable = PageRequest.of(pageSettings.getPage() - PAGE_OFFSET, pageSettings.getItemsPerPage(), sort);

        Specification<Item> itemSpecification = getItemSpecification(itemFilterForm);

        return pageDtoMapper.map(itemService.getAll(pageable, itemSpecification).map(this::toItemDto));
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
