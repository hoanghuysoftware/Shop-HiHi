package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> getAllByThumbnailNullAndImagesEmpty();
    List<Product> getAllByThumbnailNotNullAndImagesNotEmpty();

    @Query("select p from Product p where p.isActive=false")
    List<Product> getAllByActiveIsFalse();
}
