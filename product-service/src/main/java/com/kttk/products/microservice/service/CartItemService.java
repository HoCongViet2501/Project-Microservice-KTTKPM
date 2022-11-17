package com.kttk.products.microservice.service;

import com.kttk.products.microservice.entity.CartItem;

import java.util.List;

public interface CartItemService {
    public List<CartItem> getCartItemsByCartId(Integer cartId);
    public boolean addCartItem(CartItem cartItem);
    public boolean deleteCartItem(Integer cartItemId);
    public boolean updateCartItem(CartItem cartItem);
}
