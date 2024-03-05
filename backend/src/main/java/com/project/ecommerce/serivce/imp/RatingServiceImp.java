package com.project.ecommerce.serivce.imp;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.Rating;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.RatingRepository;
import com.project.ecommerce.request.RatingRequest;
import com.project.ecommerce.serivce.ProductService;
import com.project.ecommerce.serivce.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RatingServiceImp implements RatingService {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    ProductService productService;
    @Override
    public Rating createRating(RatingRequest req, Users users) throws ProductException {

        Product product = productService.findProductById(req.getProductId());

        Rating rating = new Rating();
        rating.setProduct(product);
        rating.setUser(users);
        rating.setRating(req.getRating());
        rating.setCreatedAt(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductRating(Long productId) {
        return ratingRepository.getAllProductRating(productId);
    }
}
