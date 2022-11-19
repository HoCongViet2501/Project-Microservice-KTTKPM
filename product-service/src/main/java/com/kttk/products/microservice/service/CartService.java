package com.kttk.products.microservice.service;

import com.kttk.products.microservice.entity.Cart;

public interface CartService {
    public Cart getCartByUserId(Integer userId);
    public boolean purchaseCart(Integer cartId);

    public Cart getCartById(Integer cartId);

    public void updateCart(Integer cartId);
}
