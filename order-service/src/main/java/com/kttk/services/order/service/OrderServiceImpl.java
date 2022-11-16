package com.kttk.services.order.service;

import com.kttk.services.order.entity.Orders;
import com.kttk.services.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;


    @Override
    public List<Orders> getAllOrdersByUserId(Integer userId) {
        return orderRepository.findAllByUserId(userId);
    }

    @Override
    public Orders getOrderById(Integer id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    @Override
    public Orders updateOrder(Orders order) {
        return orderRepository.save(order);
    }
}
