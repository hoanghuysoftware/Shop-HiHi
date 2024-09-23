package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
    Brand getBrandByName(String name);

    @Query("select b from Brand b where b.isActive = true")
    List<Brand> getBrandByActiveTrue();
}
