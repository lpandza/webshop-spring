package com.example.webshop.form;


import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class BrandForm {

    @NotBlank(message = "Name must not be empty")
    private String name;

}
