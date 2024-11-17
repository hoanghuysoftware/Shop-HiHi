package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> getOrdersByUserId(long userId);

    @Query(value = """
            SELECT
                YEAR(co.order_date) AS year,
                MONTH(co.order_date) AS month,
                SUM(od.total_price) AS total_revenue
            FROM customer_order co
            JOIN order_detail od ON co.id = od.order_id
            WHERE YEAR(co.order_date) = :year 
            GROUP BY year, month
            ORDER BY month;
            """, nativeQuery = true)
    List<Object[]> getMonthlyRevenue(@Param("year") int year);
}
