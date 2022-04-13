package com.example.webshop.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BrandForm {

    @NotBlank(message = "Name must not be empty")
    private String name;

}
