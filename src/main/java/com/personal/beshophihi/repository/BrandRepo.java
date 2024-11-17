package com.personal.beshophihi.repository;

import com.personal.beshophihi.dto.ReportByBrandDTO;
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

    @Query(value = """
                SELECT b.id AS brandId, b.name AS brandName, COALESCE(SUM(od.quantity), 0) AS totalSold
                FROM brand b
                LEFT JOIN product p ON b.id = p.brand_id
                LEFT JOIN order_detail od ON od.product_id = p.id
                GROUP BY b.id, b.name
            """, nativeQuery = true)
    List<Object[]> getProductSellByBrand();
}
