package com.project.ecommerce.serivce.imp;

import com.project.ecommerce.exeption.OrderException;
import com.project.ecommerce.model.*;
import com.project.ecommerce.repository.AddressRepository;
import com.project.ecommerce.repository.OrderItemRepository;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.serivce.CartService;
import com.project.ecommerce.serivce.OrderItemService;
import com.project.ecommerce.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartService cartService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrderItemRepository orderItemRepository;


    @Override
    public Orders createOrder(Users users, Address shippingAddress) {

        shippingAddress.setUser(users);
        Address address = addressRepository.save(shippingAddress);
        users.getAddress().add(address);
        userRepository.save(users);

        Cart cart = cartService.findUserCart(users.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item: cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setSize(item.getSize());
            orderItem.setUserId(item.getUserId());
            orderItem.setDiscountedPrice(item.getDiscountedPrice());

            OrderItem createOrderItem = orderItemRepository.save(orderItem);

            orderItems.add(createOrderItem);
        }

        Orders createOrder = new Orders();
        createOrder.setUsers(users);
        createOrder.setOrderItems(orderItems);
        createOrder.setTotalPrice(cart.getTotalPrice());
        createOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
        createOrder.setDiscount(cart.getDiscount());
        createOrder.setTotalItem(cart.getTotalItem());

        createOrder.setShippingAddress(shippingAddress);
        createOrder.setOrderDate(LocalDateTime.now());
        createOrder.setOrderStatus("PENDING");
        createOrder.getPaymentDetails().setPaymentStatus("PENDING");
        createOrder.setCreateAt(LocalDateTime.now());

        Orders saveOrder = orderRepository.save(createOrder);

//        for (OrderItem item: orderItems) {
////            item.setOrder(saveOrder);
////            orderItemRepository.save(item);
////        }

        return saveOrder;
    }

    @Override
    public Orders findOrderById(Long orderId) throws OrderException {

        Optional<Orders> opt = orderRepository.findById(orderId);

        if ((opt.isPresent())) {
            return opt.get();
        }
        throw new OrderException("Order not exist with id" + orderId);
    }

    @Override
    public List<Orders> usersOrderHistory(Long userId) {

        List<Orders> orders = orderRepository.getUserOrder(userId);
        return orders;
    }

    @Override
    public Orders placedOrder(Long orderId) throws OrderException {

        Orders orders = findOrderById(orderId);
        orders.setOrderStatus("PLACED");
        orders.getPaymentDetails().setPaymentStatus("COMPLETED");

        return orders;
    }

    @Override
    public Orders confirmedOrder(Long orderId) throws OrderException {

        Orders orders = findOrderById(orderId);
        orders.setOrderStatus("CONFIRMED");

        return orderRepository.save(orders);
    }

    @Override
    public Orders shippedOrder(Long orderId) throws OrderException {

        Orders orders = findOrderById(orderId);
        orders.setOrderStatus("SHIPPED");

        return orderRepository.save(orders);
    }

    @Override
    public Orders deliveredOrder(Long orderId) throws OrderException {

        Orders orders = findOrderById(orderId);
        orders.setOrderStatus("DELIVERED");

        return orderRepository.save(orders);
    }

    @Override
    public Orders canceledOrder(Long orderId) throws OrderException {

        Orders orders = findOrderById(orderId);
        orders.setOrderStatus("CANCELLED");

        return orderRepository.save(orders);
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderException {
        Orders orders = findOrderById(orderId);

        orderRepository.deleteById(orderId);
    }
}
