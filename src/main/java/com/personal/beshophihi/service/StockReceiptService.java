package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.StockReceiptDTO;
import com.personal.beshophihi.model.StockReceipt;

import java.util.List;

public interface StockReceiptService {
    List<StockReceipt> getAllStockReceipts();
    StockReceipt getStockReceipt(Long id);
    StockReceipt addStockReceipt(StockReceiptDTO stockReceiptDTO);
    StockReceipt updateStockReceipt(Long id, StockReceiptDTO stockReceiptDTO);
    StockReceipt deleteStockReceipt(Long id);
}
