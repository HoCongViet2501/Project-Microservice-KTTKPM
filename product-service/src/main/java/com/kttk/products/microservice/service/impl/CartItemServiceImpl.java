package com.kttk.products.microservice.service.impl;

import com.kttk.products.microservice.entity.CartItem;
import com.kttk.products.microservice.repository.CartItemRepository;
import com.kttk.products.microservice.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    @Cacheable(value = "CartItemByCartId", key = "#cartId")
    public List<CartItem> getCartItemsByCartId(Integer cartId) {
        return cartItemRepository.getCartItemsByCartId(cartId);
    }

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    @CacheEvict(value = "CartItem", key = "#cartItemId")
    public void deleteCartItem(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    @CachePut(value = "CartItem", key = "#cartItem.id")
    public CartItem updateCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    @Cacheable(value = "CartItem", key = "#cartItemId")
    public CartItem getCartItemById(Integer cartItemId) {
        return cartItemRepository.findById(cartItemId).get();
    }
}
