package com.personal.beshophihi.service;

import com.personal.beshophihi.model.Review;
import com.personal.beshophihi.model.ReviewDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    List<ReviewDTO> getReviewsByProductId(Long idProduct);
    Review createNewReview( ReviewDTO reviewDTO);
    List<Long> getProductExistReviewByOrderId(Long orderId);
}
