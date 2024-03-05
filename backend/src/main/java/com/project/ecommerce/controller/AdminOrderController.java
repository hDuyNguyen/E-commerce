package com.project.ecommerce.controller;

import com.project.ecommerce.exeption.OrderException;
import com.project.ecommerce.model.Orders;
import com.project.ecommerce.response.ApiResponse;
import com.project.ecommerce.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Orders>> getAllOrdersHandler() {
        List<Orders> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{orderId}/confirm")
    public ResponseEntity<Orders> confirmOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException {
        Orders orders = orderService.confirmedOrder(orderId);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/ship")
    public ResponseEntity<Orders> shippedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException {
        Orders orders = orderService.shippedOrder(orderId);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/deliver")
    public ResponseEntity<Orders> deliveredOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException {
        Orders orders = orderService.deliveredOrder(orderId);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Orders> canceledOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException {
        Orders orders = orderService.canceledOrder(orderId);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/delete")
    public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException {
        orderService.deleteOrder(orderId);

        ApiResponse res = new ApiResponse();
        res.setMessage("Order deleted successfully");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
