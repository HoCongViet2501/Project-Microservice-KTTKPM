package com.kttk.services.order.service;

import com.kttk.services.order.entity.Orders;
import com.kttk.services.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    @Cacheable(value = "OrdersByUserId", key = "#userId")
    public List<Orders> getAllOrdersByUserId(Integer userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    @Cacheable(value = "Order", key = "#id")
    public Orders getOrderById(Integer id) {
        return orderRepository.findById(id).get();
    }

    @Override
//    @CachePut(value = "Order", key = "#order.id")
    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Override
//    @CachePut(value = "Order", key = "#order.id")
    public Orders updateOrder(Orders order) {
        return orderRepository.save(order);
    }
}
