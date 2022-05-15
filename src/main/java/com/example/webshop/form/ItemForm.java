package com.example.webshop.form;

import com.example.webshop.entity.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemForm {
    @NotBlank(message = "Name must not be empty")
    private String name;

    @Size(max = 250, message = "Description too long")
    private String description;

    @NotNull(message = "Price must not be empty")
    @Positive(message = "Price must be positive number")
    private Double price;

    @NotNull(message = "Quantity must not be empty")
    @PositiveOrZero(message = "Quantity must be positive number or zero")
    private Integer quantity;

    @NotNull(message = "Brand must not be empty")
    private Brand brand;

}
