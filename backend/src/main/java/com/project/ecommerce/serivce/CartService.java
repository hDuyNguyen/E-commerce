package com.project.ecommerce.serivce;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.request.AddItemRequest;

public interface CartService {

    public Cart createCart(Users users);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
    public Cart findUserCart(Long userId);
}
