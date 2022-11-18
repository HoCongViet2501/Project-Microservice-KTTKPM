package com.kttk.products.microservice.service;

import com.kttk.products.microservice.entity.CartItem;

import java.util.List;

public interface CartItemService {
    public List<CartItem> getCartItemsByCartId(Integer cartId);
    public CartItem addCartItem(CartItem cartItem);
    public void deleteCartItem(Integer cartItemId);
    public CartItem updateCartItem(CartItem cartItem);
}
