package com.personal.beshophihi.service;

import com.personal.beshophihi.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProductNotActive();
    List<Product> getAllProductActive();
    List<Product> getAllProductActiveFalse();
    Product getProductById(Long id);
}
