package com.personal.beshophihi.service;

import com.personal.beshophihi.model.Review;
import com.personal.beshophihi.model.ReviewDTO;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByProductId(Long idProduct);
    Review createNewReview( ReviewDTO reviewDTO);

}
