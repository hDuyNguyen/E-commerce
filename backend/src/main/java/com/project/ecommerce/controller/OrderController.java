package com.project.ecommerce.controller;

import com.project.ecommerce.exeption.OrderException;
import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Address;
import com.project.ecommerce.model.Orders;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.serivce.OrderService;
import com.project.ecommerce.serivce.UserService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    OrderRepository orderRepository;


    @PostMapping("/")
    public ResponseEntity<Orders> createOrder(@RequestBody Address shippingAddress, @RequestHeader("Authorization")String jwt) throws UserException {
        Users users = userService.findUserProfileBtJwt(jwt);

        Orders orders = orderService.createOrder(users, shippingAddress);

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Orders>> userOrderHistory(@RequestHeader("Authorization")String jwt) throws UserException {
        Users users = userService.findUserProfileBtJwt(jwt);

        List<Orders> orders = orderService.usersOrderHistory(users.getId());

        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> findOrderById(@PathVariable("id")Long orderId, @RequestHeader("Authorization")String jwt ) throws UserException, OrderException {

        Users users = userService.findUserProfileBtJwt(jwt);
        Orders orders = orderService.findOrderById(orderId);

        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }
}
