package com.personal.beshophihi.service.serviceIMPL;

import com.personal.beshophihi.exception.EntityNotFound;
import com.personal.beshophihi.model.Order;
import com.personal.beshophihi.model.Product;
import com.personal.beshophihi.model.Review;
import com.personal.beshophihi.model.ReviewDTO;
import com.personal.beshophihi.repository.ProductRepo;
import com.personal.beshophihi.repository.ReviewRepo;
import com.personal.beshophihi.service.OrderService;
import com.personal.beshophihi.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceIMPL implements ReviewService {
    private final ReviewRepo reviewRepo;
    private final ProductRepo productRepo;
    private final OrderService orderService;

    @Override
    public List<ReviewDTO> getReviewsByProductId(Long idProduct) { // Pagination is needed here
        List<Review> reviewList = reviewRepo.getReviewsByProductId(idProduct);
        return reviewList
                .stream()
                .map(ReviewDTO::convertToDTO)
                .toList();
    }

    @Override
    public Review createNewReview(ReviewDTO reviewDTO) {
        Long idProduct = reviewDTO.getIdProduct();
        Long idOrder = reviewDTO.getIdOrder();
        Product product = productRepo.findById(idProduct).orElseThrow(
                () -> new EntityNotFound("Not found product with id: "+ idProduct)
        );
        Order order = orderService.getOrderById(idOrder);

        Review review = Review.builder()
                .content(reviewDTO.getContent())
                .rating(reviewDTO.getRating())
                .dateTime(LocalDateTime.now())
                .order(order)
                .product(product)
                .build();

        return reviewRepo.save(review);
    }

    @Override
    public List<Long> getProductExistReviewByOrderId(Long orderId) {
        return reviewRepo.getProductExistedReview(orderId);
    }
}
