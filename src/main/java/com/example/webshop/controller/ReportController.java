package com.example.webshop.controller;

import com.example.webshop.dto.ItemDto;
import com.example.webshop.dto.ProfitByQuarterDto;
import com.example.webshop.facade.ReportFacade;
import com.example.webshop.form.ReportForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/report")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

    private final ReportFacade reportFacade;

    public ReportController(ReportFacade reportFacade) {
        this.reportFacade = reportFacade;
    }

    @GetMapping("/bestSelling")
    public ResponseEntity<ItemDto> getBestSellingItem(){
        return reportFacade.getBestSellingItemLastWeek()
                .map((itemDto) -> ResponseEntity.status(HttpStatus.OK).body(itemDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/profit")
    public ResponseEntity<ProfitByQuarterDto> getQuarterlyProfitByYear(@RequestBody @Valid ReportForm reportForm){
        return reportFacade.getQuarterlyProfitByYear(reportForm.getYear())
                .map((profitByQuarterDto) -> ResponseEntity.status(HttpStatus.OK).body(profitByQuarterDto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
