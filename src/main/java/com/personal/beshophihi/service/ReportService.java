package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.ReportByBrandDTO;
import com.personal.beshophihi.dto.ReportByYearDTO;
import com.personal.beshophihi.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ReportService {
    Map<Long, BigDecimal> getTop3PopularProducts();
    List<Integer> getWeeklySalesReport();
    List<ReportByYearDTO> getYearlySalesReport(int year);
    List<ReportByYearDTO> getYearlyImportReport(int year);
    List<ReportByBrandDTO> getSalesReportByBrand();
}
