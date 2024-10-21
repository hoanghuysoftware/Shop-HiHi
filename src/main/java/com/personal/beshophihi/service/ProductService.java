package com.personal.beshophihi.service;

import com.personal.beshophihi.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> searchProduct(int page, int size, String keyword);
    Page<Product> getProductPublic(int page, int size, Long idBrand);
    List<Product> getAllProductActive();
    List<Product> getAllProductNotActive();
    List<Product> getAllProductActiveFalse();
    Product getProductById(Long id);
}
