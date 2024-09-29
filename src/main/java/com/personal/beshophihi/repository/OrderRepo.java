package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> getOrdersByUserId(long userId);
}
