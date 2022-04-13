package com.example.webshop.controller;

import com.example.webshop.dto.BrandDto;
import com.example.webshop.facade.BrandFacade;
import com.example.webshop.form.BrandForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/brand")
public class BrandController {

    private final BrandFacade brandFacade;

    public BrandController(BrandFacade brandFacade) {
        this.brandFacade = brandFacade;
    }

    @GetMapping
    public List<BrandDto> getAll(){
        return brandFacade.getAll();
    }

    @GetMapping(params = "id")
    public ResponseEntity<BrandDto> getBrandById(@RequestParam Long id){
        return brandFacade.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BrandDto> saveBrand(@Valid @RequestBody final BrandForm brandForm){
        return brandFacade.save(brandForm)
                .map((brandDto)-> ResponseEntity.status(HttpStatus.CREATED).body(brandDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteBrand(@PathVariable Long id){
        Optional<BrandDto> optionalBrandDTO = brandFacade.deleteById(id);

        if (optionalBrandDTO.isPresent()) return ResponseEntity.ok().build();

        return ResponseEntity.noContent().build();
    }

}
