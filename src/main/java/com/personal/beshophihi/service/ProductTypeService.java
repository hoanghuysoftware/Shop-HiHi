package com.personal.beshophihi.service;

import com.personal.beshophihi.dto.TypeProductDTO;
import com.personal.beshophihi.model.TypeProduct;

import java.util.List;

public interface ProductTypeService {
    List<TypeProduct> getAllProductType();
    TypeProduct getProductTypeById(Long id);
    TypeProduct createProductType(TypeProductDTO typeProductDTO);
    TypeProduct updateProductType(Long id,TypeProductDTO typeProductDTO);
    void deleteProductType(Long id);
    TypeProduct updateActiveTypeProduct(Long id, Boolean active);
}
