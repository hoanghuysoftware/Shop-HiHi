package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.StockReceiptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockReceiptDetailRepo extends JpaRepository<StockReceiptDetail, Long> {
}
