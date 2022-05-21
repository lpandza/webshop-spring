package com.example.webshop.controller;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.dto.PageDto;
import com.example.webshop.specification.ItemPageSettings;
import com.example.webshop.facade.ItemFacade;
import com.example.webshop.form.ItemFilterForm;
import com.example.webshop.form.ItemForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/item")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {

    private final ItemFacade itemFacade;

    public ItemController(ItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    @GetMapping
    public PageDto<ItemDto> getAll(ItemPageSettings itemPageSettings, ItemFilterForm itemFilterForm){
        return itemFacade.getAll(itemPageSettings, itemFilterForm);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Long id){
        return itemFacade.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<ItemDto> save(@Valid @RequestBody ItemForm itemForm){
        return itemFacade.save(itemForm)
                .map((itemDto)-> ResponseEntity.status(HttpStatus.CREATED).body(itemDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteItem(@PathVariable Long id){
        Optional<ItemDto> itemDto = itemFacade.deleteById(id);

        if (itemDto.isPresent()) return ResponseEntity.ok().build();

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Long id,
                                              @Valid @RequestBody ItemForm itemForm){

        return itemFacade.update(id, itemForm)
                .map(itemDto -> ResponseEntity.status(HttpStatus.OK).body(itemDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
