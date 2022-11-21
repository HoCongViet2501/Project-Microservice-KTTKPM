package com.kttk.products.microservice.controller;

import com.kttk.products.microservice.dto.request.OrdersRequest;
import com.kttk.products.microservice.dto.request.PlaceOrderRequest;
import com.kttk.products.microservice.entity.Cart;
import com.kttk.products.microservice.service.CartService;
import com.kttk.products.microservice.util.RestAddressService;
import com.kttk.products.microservice.util.RestOrderService;
import com.kttk.products.microservice.util.RestUserService;
import com.netflix.discovery.converters.Auto;
import dto.response.AddressResponse;
import dto.response.OrderResponse;
import dto.response.UserResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private RestOrderService restOrderService;
    @Autowired
    private RestAddressService restAddressService;
    @Autowired
    private RestUserService restUserService;

    @GetMapping("/user/{user_id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("user_id") Integer userId) {
        return ResponseEntity.ok().body(cartService.getCartByUserId(userId));
    }

    @GetMapping("/{cart_id}")
    public ResponseEntity<Cart> getCartByCartId(@PathVariable("cart_id") Integer cartId) {
        return ResponseEntity.ok().body(cartService.getCartById(cartId));
    }

    @PostMapping("/purchase/{cart_id}")
    public ResponseEntity<?> purchaseCart(@PathVariable("cart_id") Integer cartId, @RequestBody PlaceOrderRequest ordersRequest) {
        Cart cart = cartService.getCartById(cartId);
        UserResponse user = restUserService.getUserById(cart.getUserId());
        AddressResponse address = restAddressService.getAddressById(ordersRequest.getAddressId());
        OrdersRequest orders = new OrdersRequest();
        orders.setUserId(cart.getUserId());
        orders.setAddress(address.getAddress());
        orders.setEmail(user.getEmail());
        orders.setPhone(user.getPhone());
        orders.setFullname(user.getFullName());
        orders.setCartId(cartId);
        orders.setStatus("CREATED");
        cartService.purchaseCart(cartId);
        OrderResponse or = restOrderService.createOrder(orders);
        return ResponseEntity.ok().body(or);
    }

    @PutMapping("/{cart_id}")
    public ResponseEntity<?> updateCart(@PathVariable("cart_id") Integer cartId) {
        cartService.updateCart(cartId);
        return ResponseEntity.ok().body("Cart updated");
    }
}
