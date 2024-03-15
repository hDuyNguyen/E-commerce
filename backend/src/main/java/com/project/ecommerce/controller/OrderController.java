package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CreateOrderRequest;
import com.project.mdyshop.dto.request.StatusOrderRequest;
import com.project.mdyshop.exception.CouponException;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Order;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.OrderService;
import com.project.mdyshop.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/order")
@PreAuthorize("hasRole('USER')")
public class OrderController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestHeader("Authorization")String jwt,
                                             @RequestBody CreateOrderRequest request)
            throws UserException, CouponException {

        User user = userService.findUserByToken(jwt);

        Order order = orderService.createOrder(user, request);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateStatusOrder(@RequestBody StatusOrderRequest request,
                                                   @PathVariable Long orderId) {
        Order order = orderService.updateOrderStatus(orderId, request.getStatus());

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getListUserOrder(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);

        List<Order> orders = orderService.getListUserOrders(user.getId());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getUserOrder(@PathVariable Long orderId) {
        Order order = orderService.getUserOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
