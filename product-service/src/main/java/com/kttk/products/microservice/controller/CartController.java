package com.kttk.products.microservice.controller;

import com.kttk.products.microservice.entity.Cart;
import com.kttk.products.microservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/user/{user_id}")
    public ResponseEntity<Cart> getCartById(@PathVariable("user_id") Integer userId) {
        return ResponseEntity.ok().body(cartService.getCartByUserId(userId));
    }

    @PostMapping("/purchase/{cart_id}")
    public ResponseEntity<?> purchaseCart(@PathVariable("cart_id") Integer cartId) {
        cartService.purchaseCart(cartId);
        return ResponseEntity.ok().body("Cart has been purchased successfully.");
    }
}
