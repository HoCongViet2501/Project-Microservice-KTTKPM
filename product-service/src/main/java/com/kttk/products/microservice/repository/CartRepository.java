package com.kttk.products.microservice.repository;

import com.kttk.products.microservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

@Repository
@RedisHash("Cart")
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserId(Integer userId);

    Cart findByUserIdAndIsActiveAndIsPurchased(Integer userId, boolean b, boolean b1);
}