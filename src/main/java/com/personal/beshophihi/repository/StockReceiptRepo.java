package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.StockReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockReceiptRepo extends JpaRepository<StockReceipt, Long> {
    @Query(value = """
                SELECT
                    YEAR(sr.receipt_date) AS year,
                    MONTH(sr.receipt_date) AS month,
                    SUM(srd.total_price) AS total_import_value
                FROM stock_receipt sr
                JOIN stock_receipt_detail srd ON sr.id = srd.stock_id
                WHERE YEAR(sr.receipt_date) = :year
                GROUP BY year, month
                ORDER BY month;
            """, nativeQuery = true)
    List<Object[]> getMonthlyImportValue (@Param("year") int year);
}
