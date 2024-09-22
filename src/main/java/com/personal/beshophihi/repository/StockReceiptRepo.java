package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.StockReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockReceiptRepo extends JpaRepository<StockReceipt, Long> {
}
