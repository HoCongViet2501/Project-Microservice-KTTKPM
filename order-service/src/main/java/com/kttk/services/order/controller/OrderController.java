package com.kttk.services.order.controller;

import com.kttk.services.order.entity.Orders;
import com.kttk.services.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/orders")
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
}
