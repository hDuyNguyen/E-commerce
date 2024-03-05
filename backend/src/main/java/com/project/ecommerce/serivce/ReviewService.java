package com.project.ecommerce.serivce;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.model.Review;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.request.ReviewRequest;

import java.util.List;

public interface ReviewService {

    public Review createReview(ReviewRequest req, Users users) throws ProductException;
    public List<Review> getAllReview(Long productId);
}
