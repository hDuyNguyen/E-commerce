package com.project.ecommerce.controller;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Review;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.request.ReviewRequest;
import com.project.ecommerce.serivce.ReviewService;
import com.project.ecommerce.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    @Autowired
    ReviewService reviewService;
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest request, @RequestHeader("Authorization")String jwt) throws UserException, ProductException {

        Users users = userService.findUserProfileBtJwt(jwt);
        Review review = reviewService.createReview(request, users);

        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReview(@PathVariable Long productId) throws UserException, ProductException {

        List<Review> reviews = reviewService.getAllReview(productId);

        return new ResponseEntity<>(reviews, HttpStatus.ACCEPTED);
    }
}
