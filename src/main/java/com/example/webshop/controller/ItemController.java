package com.example.webshop.controller;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.facade.ItemFacade;
import com.example.webshop.form.ItemForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/item")
public class ItemController {

    private final ItemFacade itemFacade;

    public ItemController(ItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    @GetMapping
    public List<ItemDto> getAll(){
        return itemFacade.getAll();
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

}
