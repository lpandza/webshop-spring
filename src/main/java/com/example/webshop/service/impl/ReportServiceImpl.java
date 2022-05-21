package com.example.webshop.service.impl;

import com.example.webshop.dto.ProfitByQuarterDto;
import com.example.webshop.entity.Item;
import com.example.webshop.repository.ReportRepository;
import com.example.webshop.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Optional<Item> getBestSellingItemLastWeek() {
        return reportRepository.getBestSellingItemLastWeek();
    }

    @Override
    public Optional<ProfitByQuarterDto> getQuarterlyProfitByYear(Integer year) {
        return reportRepository.getQuarterlyProfitByYear(year);
    }
}
