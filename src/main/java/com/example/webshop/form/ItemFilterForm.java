package com.example.webshop.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemFilterForm {

    private Double minPrice;
    private Double maxPrice;
    private Long[] brands;

}
