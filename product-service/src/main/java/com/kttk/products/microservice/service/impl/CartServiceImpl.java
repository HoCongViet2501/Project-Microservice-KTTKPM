package com.kttk.products.microservice.service.impl;

import com.kttk.products.microservice.entity.Cart;
import com.kttk.products.microservice.entity.CartItem;
import com.kttk.products.microservice.repository.CartRepository;
import com.kttk.products.microservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
//    @Cacheable(value = "Cart")
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
//    @CachePut(value = "Cart", key = "#cartId")
    public boolean purchaseCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(()
        -> new RuntimeException("Cart not found"));
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

    @Override
    @Cacheable(value = "Cart", key = "#cartId")
    public Cart getCartById(Integer cartId) {
        return cartRepository.findById(cartId).get();
    }

    @Override
    @CachePut(value = "Cart", key = "#cartId")
    public void updateCart(Integer cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        Double totalPrice = 0.0;
        Integer totalItems = 0;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice += cartItem.getQuantity() * cartItem.getProduct().getPrice();
            totalItems += cartItem.getQuantity();
        }
        cart.setTotalPrice(totalPrice);
        cart.setTotalItems(totalItems);
        cartRepository.save(cart);
    }
}
