package com.kttk.services.order.controller;

import com.kttk.services.order.dto.CartResponse;
import com.kttk.services.order.dto.OrderResponse;
import com.kttk.services.order.entity.Orders;
import com.kttk.services.order.service.OrderService;
import com.kttk.services.order.util.RestCartService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
//@CircuitBreaker(name= "service-java")
@Retry(name = "service-java")
//@TimeLimiter(name = "service-java")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RestCartService restCartService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersByUserId(@PathVariable Integer userId) {
        List<OrderResponse> orderResponses = new ArrayList<OrderResponse>();
        List<Orders> orders = orderService.getAllOrdersByUserId(userId);
        for (Orders or: orders) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(or.getId());
            orderResponse.setUserId(or.getUserId());
            orderResponse.setAddress(or.getAddress());
            orderResponse.setEmail(or.getEmail());
            orderResponse.setPhone(or.getPhone());
            orderResponse.setFullname(or.getFullname());
            orderResponse.setStatus(or.getStatus());
            CartResponse cartResponse = restCartService.getCartById(or.getCartId());
            orderResponse.setCart(cartResponse);
            orderResponses.add(orderResponse);
        }
        return ResponseEntity.ok(orderResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer id) {
        Orders order = orderService.getOrderById(id);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setAddress(order.getAddress());
        orderResponse.setEmail(order.getEmail());
        orderResponse.setPhone(order.getPhone());
        orderResponse.setFullname(order.getFullname());
        orderResponse.setStatus(order.getStatus());
        CartResponse cartResponse = restCartService.getCartById(order.getCartId());
        orderResponse.setCart(cartResponse);
        return ResponseEntity.ok(orderResponse);
    }

    @PostMapping("")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody Orders order) {
        Orders newOrder = orderService.createOrder(order);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(newOrder.getId());
        orderResponse.setUserId(newOrder.getUserId());
        orderResponse.setAddress(newOrder.getAddress());
        orderResponse.setEmail(newOrder.getEmail());
        orderResponse.setPhone(newOrder.getPhone());
        orderResponse.setFullname(newOrder.getFullname());
        orderResponse.setStatus(newOrder.getStatus());
        CartResponse cartResponse = restCartService.getCartById(newOrder.getCartId());
        orderResponse.setCart(cartResponse);
        return ResponseEntity.ok(orderResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody Orders order) {
        Orders newOrder = orderService.updateOrder(order);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(newOrder.getId());
        orderResponse.setUserId(newOrder.getUserId());
        orderResponse.setAddress(newOrder.getAddress());
        orderResponse.setEmail(newOrder.getEmail());
        orderResponse.setPhone(newOrder.getPhone());
        orderResponse.setFullname(newOrder.getFullname());
        orderResponse.setStatus(newOrder.getStatus());
        CartResponse cartResponse = restCartService.getCartById(newOrder.getCartId());
        orderResponse.setCart(cartResponse);
        return ResponseEntity.ok(orderResponse);
    }
}
