package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> getReviewsByProductId( Long productId);

    @Query("SELECT DISTINCT od.product.id FROM Order o " +
            "JOIN o.orderDetails od " +
            "WHERE o.id = :orderId AND EXISTS (SELECT r FROM Review r WHERE r.product.id = od.product.id AND r.order.id = o.id)")
    List<Long> getProductExistedReview(@Param("orderId") Long orderId);

}
