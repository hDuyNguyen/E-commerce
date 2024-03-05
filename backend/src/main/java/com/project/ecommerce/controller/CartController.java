package com.project.ecommerce.controller;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.request.AddItemRequest;
import com.project.ecommerce.response.ApiResponse;
import com.project.ecommerce.serivce.CartService;
import com.project.ecommerce.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    @GetMapping("/")
//    @ManagedOperation(description = "find cart by user id")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt) throws UserException {

        Users users = userService.findUserProfileBtJwt(jwt);
        Cart cart = cartService.findUserCart(users.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
//    @ManagedOperation(description = "add item to cart")
    public ResponseEntity<ApiResponse> adItemToCart(@RequestBody AddItemRequest request, @RequestHeader("Authorization")String jwt) throws UserException, ProductException {

        Users users = userService.findUserProfileBtJwt(jwt);

        cartService.addCartItem(users.getId(), request);

        ApiResponse response = new ApiResponse();
        response.setMessage("Item added to cart");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
