package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepo extends JpaRepository<OrderDetail, Long> {

    @Query(value = "SELECT p.id, SUM(o.quantity) as sell FROM order_detail as o JOIN" +
            " product as p ON o.product_id = p.id GROUP BY p.id ORDER BY sell DESC LIMIT 3",
            nativeQuery = true)
    List<Object[]> getTop3SellProduct();
}
