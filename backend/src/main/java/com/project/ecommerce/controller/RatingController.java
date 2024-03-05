package com.project.ecommerce.controller;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Rating;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.request.RatingRequest;
import com.project.ecommerce.serivce.RatingService;
import com.project.ecommerce.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {

    @Autowired
    UserService userService;
    @Autowired
    RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest request, @RequestHeader("Authorization")String jwt) throws UserException, ProductException{

        Users users = userService.findUserProfileBtJwt(jwt);
        Rating rating = ratingService.createRating(request, users);

        return new ResponseEntity<>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductRating(@PathVariable Long productId, @RequestHeader("Authorization")String jwt) throws UserException, ProductException {

        Users users = userService.findUserProfileBtJwt(jwt);

        List<Rating> ratings = ratingService.getProductRating(productId);

        return new ResponseEntity<>(ratings, HttpStatus.CREATED);
    }
}
