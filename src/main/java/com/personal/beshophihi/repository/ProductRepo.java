package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> getAllByThumbnailNullAndImagesEmpty();
    List<Product> getAllByThumbnailNotNullAndImagesNotEmpty();

    @Query("SELECT p FROM Product as p WHERE p.brand.id = :brandId")
    Page<Product> getProductsByBrand(@Param("brandId") Long brandId, Pageable pageable);

    @Query("select p from Product p where p.isActive=false")
    List<Product> getAllByActiveIsFalse();
}
