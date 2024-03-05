package com.project.ecommerce.repository;

import com.project.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepsitory extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c where c.users.id=:userId")
    public Cart findByUserId(@Param("userId")Long userId);
}
