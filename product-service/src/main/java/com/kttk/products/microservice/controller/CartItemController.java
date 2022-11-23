package com.kttk.products.microservice.controller;

import com.kttk.products.microservice.dto.request.CartItemRequest;
import com.kttk.products.microservice.entity.Cart;
import com.kttk.products.microservice.entity.CartItem;
import com.kttk.products.microservice.entity.Product;
import com.kttk.products.microservice.service.CartItemService;
import com.kttk.products.microservice.service.CartService;
import com.kttk.products.microservice.service.ProductService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart/{cart_id}")
    public ResponseEntity<List<CartItem>> getCartItemsByCartId(@PathVariable("cart_id") Integer cartId) {
        return ResponseEntity.ok().body(cartItemService.getCartItemsByCartId(cartId));
    }

    @PostMapping("")
    public ResponseEntity<CartItem> addCartItem(@RequestBody CartItemRequest cartItem) {
        Cart cart = cartService.getCartById(cartItem.getCartId());
        Product product = productService.getProductById(cartItem.getProductId());
        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setProduct(product);
        newCartItem.setQuantity(cartItem.getQuantity());
        newCartItem.setPrice(product.getPrice());
        CartItem res = cartItemService.addCartItem(newCartItem);
        if (res == null) {
            return ResponseEntity.badRequest().body(null);
        }
        cartService.updateCart(cart.getId());
        return ResponseEntity.ok().body(cartItemService.addCartItem(newCartItem));
    }

    @PutMapping("/{cart_item_id}")
    public ResponseEntity<CartItem> updateCartItem(@RequestBody CartItemRequest cartItem, @PathVariable("cart_item_id") Integer cartItemId) {
        Cart cart = cartService.getCartById(cartItem.getCartId());
        Product product = productService.getProductById(cartItem.getProductId());
        CartItem newCartItem = new CartItem();
        newCartItem.setCart(cart);
        newCartItem.setProduct(product);
        newCartItem.setQuantity(cartItem.getQuantity());
        newCartItem.setPrice(product.getPrice());
        newCartItem.setId(cartItemId);
        CartItem res = cartItemService.updateCartItem(newCartItem);
        if (res == null) {
            return ResponseEntity.badRequest().body(null);
        }
        cartService.updateCart(cart.getId());
        return ResponseEntity.ok().body(res);
    }

    @DeleteMapping("/{cart_item_id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("cart_item_id") Integer cartItemId) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        cartItemService.deleteCartItem(cartItemId);
        cartService.updateCart(cartItem.getCart().getId());
        return ResponseEntity.ok().body("Cart item has been deleted successfully.");
    }
}
