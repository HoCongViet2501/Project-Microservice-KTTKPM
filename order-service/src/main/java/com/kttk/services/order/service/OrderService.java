package com.kttk.services.order.service;

import com.kttk.services.order.entity.Orders;

import java.util.List;

public interface OrderService {
    public List<Orders> getAllOrdersByUserId(Integer userId);
    public Orders getOrderById(Integer id);
    public Orders createOrder(Orders order);
    public Orders updateOrder(Orders order);
}
