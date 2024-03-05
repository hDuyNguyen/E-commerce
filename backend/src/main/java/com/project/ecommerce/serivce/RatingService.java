package com.project.ecommerce.serivce;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.model.Rating;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.request.RatingRequest;

import java.util.List;

public interface RatingService {

    public Rating createRating(RatingRequest req, Users users) throws ProductException;
    public List<Rating> getProductRating(Long productId);
}
