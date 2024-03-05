package com.project.ecommerce.serivce.imp;

import com.project.ecommerce.model.OrderItem;
import com.project.ecommerce.repository.OrderItemRepository;
import com.project.ecommerce.repository.OrderRepository;
import com.project.ecommerce.serivce.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImp implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;
    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
}
