package com.example.webshop.facade;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.dto.ProfitByQuarterDto;

import java.util.Optional;

public interface ReportFacade {
    Optional<ItemDto> getBestSellingItemCurrentWeek();

    Optional<ProfitByQuarterDto> getQuarterlyProfitByYear(Integer year);
}
