package com.kttk.services.order.controller;

import com.kttk.services.order.entity.Orders;
import com.kttk.services.order.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
@CircuitBreaker(name= "service-java")
@Retry(name = "service-java", fallbackMethod = "fallback")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Orders>> getAllOrdersByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(orderService.getAllOrdersByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrderById(@PathVariable Integer id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> updateOrder(@RequestBody Orders order) {
        return ResponseEntity.ok(orderService.updateOrder(order));
    }

    public ResponseEntity<Object> fallback(Exception e) {
        return ResponseEntity.ok("Order service is down. Please try again later.");
    }
}
