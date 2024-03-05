package com.project.ecommerce.serivce.imp;

import com.project.ecommerce.exeption.ProductException;
import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.CartRepsitory;
import com.project.ecommerce.request.AddItemRequest;
import com.project.ecommerce.serivce.CartItemService;
import com.project.ecommerce.serivce.CartService;
import com.project.ecommerce.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    CartRepsitory cartRepsitory;
    @Autowired
    CartItemService cartItemService;
    @Autowired
    ProductService productService;

    @Override
    public Cart createCart(Users users) {

        Cart cart = new Cart();
        cart.setUsers(users);

        return cartRepsitory.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException {

        Cart cart = cartRepsitory.findByUserId(userId);
        Product product = productService.findProductById(req.getProductId());

        CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);

        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);

            int price = req.getQuantity()* product.getDiscounted_price();
            cartItem.setPrice(price);
            cartItem.setSize(req.getSize());

            CartItem createCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createCartItem);
        }

        return "Item add to cart success";
    }

    @Override
    public Cart findUserCart(Long userId) {

        Cart cart = cartRepsitory.findByUserId(userId);

        int totalPrice = 0;
        int totalDiscountedPrice = 0;
        int totalItem = 0;

        for (CartItem cartItem:cart.getCartItems()) {
            totalPrice = totalPrice + cartItem.getPrice();
            totalDiscountedPrice = totalDiscountedPrice + cartItem.getDiscountedPrice();
            totalItem = totalItem + cartItem.getQuantity();
        }

        cart.setTotalDiscountedPrice(totalDiscountedPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalPrice-totalDiscountedPrice);

        return cartRepsitory.save(cart);
    }
}
