package com.example.webshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfitByQuarterDto {

    private BigDecimal q1;
    private BigDecimal q2;
    private BigDecimal q3;
    private BigDecimal q4;

}
