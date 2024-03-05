package com.project.ecommerce.controller;

import com.project.ecommerce.exeption.CartItemException;
import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.response.ApiResponse;
import com.project.ecommerce.serivce.CartItemService;
import com.project.ecommerce.serivce.CartService;
import com.project.ecommerce.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart_item")
public class CartItemController {

    @Autowired
    CartItemService cartItemService;
    @Autowired
    UserService userService;

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId, @RequestHeader("Authorization")String jwt) throws UserException, CartItemException {
        Users users = userService.findUserProfileBtJwt(jwt);
        cartItemService.removeCartItem(users.getId(),cartItemId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Cart Item delete Successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
