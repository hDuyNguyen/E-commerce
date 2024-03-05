package com.project.ecommerce.repository;

import com.project.ecommerce.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("select o from Orders o where o.user.id=:userId and o.orderStatus = 'PLACED' or o.orderStatus = 'CONFIRMED' or o.orderStatus = 'SHIPPED' or o.orderStatus = 'DELIVERED'")
    public List<Orders> getUserOrder(@Param("userId")Long userId);
}
