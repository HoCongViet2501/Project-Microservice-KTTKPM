package com.kttk.products.microservice.repository;

import com.kttk.products.microservice.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> getCartItemsByCartId(Integer cartId);
}