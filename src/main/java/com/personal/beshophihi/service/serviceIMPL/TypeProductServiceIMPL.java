package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.dto.TypeProductDTO;
import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.exception.ExistsEntityException;
import com.personal.beshophihi.model.TypeProduct;
import com.personal.beshophihi.repository.TypeProductRepo;
import com.personal.beshophihi.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeProductServiceIMPL implements ProductTypeService {
    private final TypeProductRepo typeProductRepo;

    @Override
    public List<TypeProduct> getAllProductType() {
        return typeProductRepo.getTypeProductByActive();
    }

    @Override
    public TypeProduct getProductTypeById(Long id) {
        return typeProductRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found type product by id: " + id)
        );
    }

    @Override
    public TypeProduct createProductType(TypeProductDTO typeProductDTO) {
        TypeProduct typeProduct = typeProductRepo.getTypeProductByName(typeProductDTO.getNameType());
        if (typeProduct != null) {
            throw new ExistsEntityException("Name type products already exist");
        }
        return typeProductRepo.save(TypeProduct
                .builder()
                .isActive(true)
                .name(typeProductDTO.getNameType())
                .build()
        );
    }

    @Override
    public TypeProduct updateProductType(Long id, TypeProductDTO typeProductDTO) {
        TypeProduct old = typeProductRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found type product by id: " + id)
        );
        TypeProduct typeProduct = typeProductRepo.getTypeProductByName(typeProductDTO.getNameType());
        if (typeProduct != null) {
            throw new ExistsEntityException("Name type products already exist");
        }
        old.setName(typeProductDTO.getNameType());
        return typeProductRepo.save(old);
    }

    @Override
    public void deleteProductType(Long id) {
        TypeProduct type = typeProductRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found type product by id: " + id)
        );
        type.setActive(false);
        typeProductRepo.save(type);
    }

    @Override
    public TypeProduct updateActiveTypeProduct(Long id, Boolean active) {
        TypeProduct type = typeProductRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found type product by id: " + id)
        );
        type.setActive(active);
        return typeProductRepo.save(type);
    }
}
