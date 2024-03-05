package com.project.ecommerce.serivce.imp;

import com.project.ecommerce.exeption.CartItemException;
import com.project.ecommerce.exeption.UserException;
import com.project.ecommerce.model.Cart;
import com.project.ecommerce.model.CartItem;
import com.project.ecommerce.model.Product;
import com.project.ecommerce.model.Users;
import com.project.ecommerce.repository.CartItemRepository;
import com.project.ecommerce.repository.CartRepsitory;
import com.project.ecommerce.serivce.CartItemService;
import com.project.ecommerce.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImp implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    UserService userService;
    @Autowired
    CartRepsitory cartRepsitory;

    @Override
    public CartItem createCartItem(CartItem cartItem) {

        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscounted_price()*cartItem.getQuantity());

        CartItem createdCartItem = cartItemRepository.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

        CartItem item = findCartItemById(id);
        Users users = userService.findUserById(item.getUserId());

        if (users.getId().equals(userId)) {
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscounted_price()*item.getQuantity());
        }

        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {

        CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);

        return cartItem;
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem = findCartItemById(cartItemId);
        Users users = userService.findUserById(cartItem.getUserId());

        Users reqUser = userService.findUserById(userId);

        if (users.getId().equals(reqUser.getId())) {
            cartItemRepository.deleteById(cartItemId);
        }
        else {
            throw new UserException("You can't remove another user item");
        }
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {

        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new CartItemException("Cart Item not found with id:" + cartItemId);
    }
}
