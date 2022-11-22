package com.kttk.products.microservice.service.impl;

import com.kttk.products.microservice.entity.Cart;
import com.kttk.products.microservice.repository.CartRepository;
import com.kttk.products.microservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public Cart getCartByUserId(Integer userId) {
        // get cart by user id and is active && is purchased
        Cart cart = cartRepository.findByUserIdAndIsActiveAndIsPurchased(userId, true, false);
        if (cart == null) {
            // if cart is null, create new cart
            cart = new Cart();
            cart.setUserId(userId);
            cart.setIsActive(true);
            cart.setIsPurchased(false);
            cart.setTotalItems(0);
            cart.setTotalPrice(0.0);
            cartRepository.save(cart);
            return cart;
        }
        return cart;
    }

    @Override
    public boolean purchaseCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        if (cart == null) {
            return false;
        }
        cart.setIsPurchased(true);
        cart.setIsActive(false);
        cartRepository.save(cart);
        Cart newCart = new Cart();
        newCart.setIsActive(true);
        newCart.setIsPurchased(false);
        newCart.setTotalItems(0);
        newCart.setTotalPrice(0.0);
        newCart.setUserId(cart.getUserId());
        cartRepository.save(newCart);
        return true;
    }
}
