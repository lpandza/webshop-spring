package com.example.webshop.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiscountForm {

    @NotBlank(message = "Code must not be empty")
    @Size(min = 3, max = 20, message = "Code must be between 10 and 200 characters")
    private String code;

    @NotNull(message = "Discount percentage must not be empty")
    private Double discountPercentage;

    @NotNull(message = "IsUsed must not be empty")
    private Boolean isUsed;

}
