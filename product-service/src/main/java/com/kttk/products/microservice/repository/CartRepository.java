package com.kttk.products.microservice.repository;

import com.kttk.products.microservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserId(Integer userId);

    Cart findByUserIdAndIsActiveAndIsPurchased(Integer userId, boolean b, boolean b1);
}