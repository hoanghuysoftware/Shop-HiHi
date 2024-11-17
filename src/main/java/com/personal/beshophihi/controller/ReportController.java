package com.personal.beshophihi.controller;

import com.personal.beshophihi.dto.ReportDTO1;
import com.personal.beshophihi.dto.ResponseMessage;
import com.personal.beshophihi.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/popular-product")
    public ResponseEntity<ResponseMessage> doGet3Product(){
        Map<Long, BigDecimal> resultService = reportService.getTop3PopularProducts();
        List<ReportDTO1> result = new ArrayList<>();

        for(Map.Entry<Long, BigDecimal> item : resultService.entrySet()){
            ReportDTO1 reportDTO1 = ReportDTO1.builder()
                    .idProduct(item.getKey())
                    .quantity(item.getValue())
                    .build();
            result.add(reportDTO1);
        }
        return new ResponseEntity<>(ResponseMessage.builder()
                .code(200)
                .status("TRUE")
                .message("DO get 3 product sell best")
                .data(result)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/brand-sell")
    public ResponseEntity<ResponseMessage> doGetProductSellByBrands(){
        return new ResponseEntity<>(ResponseMessage.builder()
                .code(200)
                .status("TRUE")
                .message("Do get count product sell by brands")
                .data(reportService.getSalesReportByBrand())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/sell-by-year")
    public ResponseEntity<ResponseMessage> doGetSellByYear(@RequestParam("year") int year){
        return new ResponseEntity<>(ResponseMessage.builder()
                .code(200)
                .status("TRUE")
                .message("Do get  sell by year")
                .data(reportService.getYearlySalesReport(year))
                .build(), HttpStatus.OK);
    }
    @GetMapping("/import-by-year")
    public ResponseEntity<ResponseMessage> doGetImportByYear(@RequestParam("year") int year){
        return new ResponseEntity<>(ResponseMessage.builder()
                .code(200)
                .status("TRUE")
                .message("Do get import by year")
                .data(reportService.getYearlyImportReport(year))
                .build(), HttpStatus.OK);
    }
}
