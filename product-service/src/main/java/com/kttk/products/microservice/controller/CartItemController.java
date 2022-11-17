package com.kttk.products.microservice.controller;

import com.kttk.products.microservice.entity.CartItem;
import com.kttk.products.microservice.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/cart/{cart_id}")
    public ResponseEntity<?> getCartItemsByCartId(@PathVariable("cart_id") Integer cartId) {
        return ResponseEntity.ok().body(cartItemService.getCartItemsByCartId(cartId));
    }

    @PostMapping("")
    public ResponseEntity<?> addCartItem(@RequestBody CartItem cartItem) {
        return ResponseEntity.ok().body(cartItemService.addCartItem(cartItem));
    }

    @PutMapping("")
    public ResponseEntity<?> updateCartItem(@RequestBody CartItem cartItem) {
        return ResponseEntity.ok().body(cartItemService.updateCartItem(cartItem));
    }

    @DeleteMapping("/{cart_item_id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("cart_item_id") Integer cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return ResponseEntity.ok().body("Cart item has been deleted successfully.");
    }
}
