package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.SupplierDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.exception.ExistsEntityException;
import com.personal.beshophihi.model.Supplier;
import com.personal.beshophihi.repository.SupplierRepo;
import com.personal.beshophihi.service.SupplierService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierServiceIMPL implements SupplierService {
    private final SupplierRepo supplierRepo;


    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepo.findAll();
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found entity with id: " + id)
        );
    }

    @Override
    public Supplier createSupplier(SupplierDTO supplierDTO) {
        boolean existsName = supplierRepo.existsByName(supplierDTO.getName());
        if (existsName) {
            throw new ExistsEntityException("Supplier already exists");
        }
        Supplier supplier = Supplier.builder()
                .name(supplierDTO.getName())
                .address(supplierDTO.getAddress())
                .email(supplierDTO.getEmail())
                .phone(supplierDTO.getPhone())
                .build();
        return supplierRepo.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long id, SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found entity with id: " + id)
        );
        supplier.setName(supplierDTO.getName());
        supplier.setAddress(supplierDTO.getAddress());
        supplier.setEmail(supplierDTO.getEmail());
        supplier.setPhone(supplierDTO.getPhone());
        return supplierRepo.save(supplier);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = supplierRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found entity with id: " + id)
        );
        supplierRepo.delete(supplier);
    }
}
