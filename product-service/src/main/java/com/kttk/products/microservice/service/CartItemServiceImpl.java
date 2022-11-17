package com.kttk.products.microservice.service;

import com.kttk.products.microservice.entity.CartItem;
import com.kttk.products.microservice.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService{
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getCartItemsByCartId(Integer cartId) {
        return cartItemRepository.getCartItemsByCartId(cartId);
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
        return true;
    }

    @Override
    public boolean deleteCartItem(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
        return true;
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
        return true;
    }
}
