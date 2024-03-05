package com.project.ecommerce.serivce;

import com.project.ecommerce.exeption.OrderException;
import com.project.ecommerce.model.Address;
import com.project.ecommerce.model.Orders;
import com.project.ecommerce.model.Users;

import java.util.List;

public interface OrderService {

    public Orders createOrder(Users users, Address shippingAddress);
    public Orders findOrderById(Long orderId) throws OrderException;
    public List<Orders> usersOrderHistory(Long userId);
    public Orders placedOrder(Long orderId) throws OrderException;
    public Orders confirmedOrder(Long orderId) throws OrderException;
    public Orders shippedOrder(Long orderId) throws OrderException;
    public Orders deliveredOrder(Long orderId) throws OrderException;
    public Orders canceledOrder(Long orderId) throws OrderException;
    public List<Orders> getAllOrders();
    public void deleteOrder(Long orderId) throws OrderException;
}
