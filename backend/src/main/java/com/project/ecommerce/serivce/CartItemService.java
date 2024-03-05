package com.project.ecommerce.serivce;

import com.project.ecommerce.exeption.CartItemException;
import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.Product;

public interface CartItemService {

    public CartItem createCartItem(CartItem cartItem);
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
