package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.SupplierDTO;
import com.personal.beshophihi.model.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(Long id);
    Supplier createSupplier(SupplierDTO supplierDTO);
    Supplier updateSupplier(Long id, SupplierDTO supplierDTO);
    void deleteSupplier(Long id);
}
