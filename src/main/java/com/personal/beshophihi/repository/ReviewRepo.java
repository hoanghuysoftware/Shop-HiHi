package com.personal.beshophihi.repository;

import com.personal.beshophihi.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
    List<Review> getReviewsByProductId(long productId);
}
