package com.example.webshop.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportForm {

    @Min(value = 2020, message = "Min year is 2020")
    @Max(value = 2022, message = "Enter valid year")
    private Integer year;

}
