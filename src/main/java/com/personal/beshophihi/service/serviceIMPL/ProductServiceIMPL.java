package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.model.Product;
import com.personal.beshophihi.repository.ProductRepo;
import com.personal.beshophihi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceIMPL implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public List<Product> getAllProductNotActive() {
        return productRepo.getAllByThumbnailNullAndImagesEmpty();
    }

    @Override
    public List<Product> getAllProductActive() {
        return productRepo.getAllByThumbnailNotNullAndImagesNotEmpty();
    }

    @Override
    public List<Product> getAllProductActiveFalse() {
        return productRepo.getAllByActiveIsFalse();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(
                () -> new EntityNotFound("Not found product with id: "+id)
        );
    }
}
