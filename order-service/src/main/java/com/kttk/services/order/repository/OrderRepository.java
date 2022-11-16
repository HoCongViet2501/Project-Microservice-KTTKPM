package com.kttk.services.order.repository;

import com.kttk.services.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findAllByUserId(Integer userId);
}