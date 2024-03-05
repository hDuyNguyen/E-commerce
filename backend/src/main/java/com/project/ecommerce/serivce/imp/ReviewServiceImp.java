package com.project.ecommerce.serivce.imp;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.Review;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.ProductRepository;
import com.project.ecommerce.repository.ReviewRepository;
import com.project.ecommerce.request.ReviewRequest;
import com.project.ecommerce.serivce.ProductService;
import com.project.ecommerce.serivce.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewServiceImp implements ReviewService {

    @Autowired
    ProductService productService;
    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    ProductRepository productRepository;

    @Override
    public Review createReview(ReviewRequest req, Users users) throws ProductException {

        Product product = productService.findProductById(req.getProductId());

        Review review = new Review();
        review.setReview(req.getReview());
        review.setUser(users);
        review.setProduct(product);
        review.setCreatedAt(LocalDateTime.now());

        return  reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReview(Long productId) {

        return reviewRepository.getAllProductReview(productId);
    }
}
