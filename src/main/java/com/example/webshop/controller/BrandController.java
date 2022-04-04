package com.example.webshop.controller;

import com.example.webshop.dto.BrandDTO;
import com.example.webshop.facade.BrandFacade;
import com.example.webshop.form.BrandForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/brand")
public class BrandController {

    private final BrandFacade brandFacade;

    public BrandController(BrandFacade brandFacade) {
        this.brandFacade = brandFacade;
    }

    @GetMapping
    public List<BrandDTO> getAll(){
        return brandFacade.getAll();
    }

    @GetMapping(params = "id")
    public ResponseEntity<BrandDTO> getBrandById(@RequestParam Long id){
        return brandFacade.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<BrandDTO> saveBrand(@Valid @RequestBody final BrandForm brandForm){
        return brandFacade.save(brandForm)
                .map(brandDTO -> ResponseEntity.status(HttpStatus.CREATED).body(brandDTO))
                .get();

    }

}
