package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.ReportByBrandDTO;
import com.personal.beshophihi.dto.ReportByYearDTO;
import com.personal.beshophihi.model.Product;
import com.personal.beshophihi.repository.BrandRepo;
import com.personal.beshophihi.repository.OrderDetailRepo;
import com.personal.beshophihi.repository.OrderRepo;
import com.personal.beshophihi.repository.StockReceiptRepo;
import com.personal.beshophihi.service.ProductService;
import com.personal.beshophihi.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReportServiceIMPL implements ReportService {
    private final OrderDetailRepo orderDetailRepo;
    private final BrandRepo brandRepo;
    private final OrderRepo orderRepo;
    private final StockReceiptRepo stockReceiptRepo;
    private final ProductService productService;

    @Override
    public Map<Long, BigDecimal> getTop3PopularProducts() {
        List<Object[]> resultRepo = orderDetailRepo.getTop3SellProduct();
        Map<Long, BigDecimal> products = new LinkedHashMap<>();
        for(Object[] item : resultRepo){
            products.put((Long) item[0], (BigDecimal) item[1]);
        }

        return products;
    }

    @Override
    public List<Integer> getWeeklySalesReport() {
        return List.of();
    }

    @Override
    public List<ReportByYearDTO> getYearlySalesReport(int year) {
        List<Object[]> resultRepo = orderRepo.getMonthlyRevenue(year);
        List<ReportByYearDTO> reportByYearDTOS = new ArrayList<>();
        int[] month = new int[13];
        for(Object[] item : resultRepo){
            month[(Integer)item[1]] = 1;
            ReportByYearDTO report = ReportByYearDTO.builder()
                    .year((Integer) item[0])
                    .month((Integer) item[1])
                    .totalSold((BigDecimal) item[2])
                    .build();

            reportByYearDTOS.add(report);
        }
        for(int i=1; i<month.length; i++){
            if(month[i] == 1){
                continue;
            }
            reportByYearDTOS.add(ReportByYearDTO.builder()
                            .year(year)
                            .month(i)
                            .totalSold(BigDecimal.ZERO)
                    .build());
        }
        return reportByYearDTOS;
    }

    @Override
    public List<ReportByYearDTO> getYearlyImportReport(int year) {
        List<Object[]> resultRepo = stockReceiptRepo.getMonthlyImportValue(year);
        List<ReportByYearDTO> reportByYearDTOS = new ArrayList<>();
        int[] month = new int[13];
        for(Object[] item : resultRepo){
            month[(int)item[1]] = 1;
            ReportByYearDTO report = ReportByYearDTO.builder()
                    .year(year)
                    .month((int) item[1])
                    .totalSold((BigDecimal)item[2])
                    .build();
            reportByYearDTOS.add(report);
        }
        for(int i=1; i<13; i++){
            if(month[i] == 1){
                continue;
            }
            reportByYearDTOS.add(ReportByYearDTO.builder()
                    .year(year)
                    .month(i)
                    .totalSold(BigDecimal.ZERO)
                    .build());
        }
        return reportByYearDTOS;
    }

    @Override
    public List<ReportByBrandDTO> getSalesReportByBrand() {
        List<Object[]> resultRepo = brandRepo.getProductSellByBrand();
        List<ReportByBrandDTO> brandDTOList = new ArrayList<>();

        for(Object[] item : resultRepo){
            ReportByBrandDTO report = ReportByBrandDTO.builder()
                    .brandId((Long) item[0])
                    .brandName((String) item[1])
                    .totalSold((int) ((BigDecimal) item[2]).longValue())
                    .build();
            brandDTOList.add(report);
        }
        return brandDTOList;
    }
}
