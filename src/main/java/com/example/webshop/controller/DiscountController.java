package com.example.webshop.controller;

import com.example.webshop.dto.DiscountDto;
import com.example.webshop.entity.Discount;
import com.example.webshop.facade.DiscountFacade;
import com.example.webshop.form.DiscountForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/discount")
@CrossOrigin(origins = "http://localhost:4200")
public class DiscountController {

    private final DiscountFacade discountFacade;

    public DiscountController(DiscountFacade discountFacade) {
        this.discountFacade = discountFacade;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DiscountDto> getDiscountById(@PathVariable Long id){
        return discountFacade.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(params = "code")
    public ResponseEntity<DiscountDto> getDiscountByCode(@RequestParam String code){
        return discountFacade.getByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DiscountDto> saveDiscount(@RequestBody DiscountForm discountForm){
        return discountFacade.save(discountForm)
                .map(discountDto -> ResponseEntity.status(HttpStatus.CREATED).body(discountDto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
