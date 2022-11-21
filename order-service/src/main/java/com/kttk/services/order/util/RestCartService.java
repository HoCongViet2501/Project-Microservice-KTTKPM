package com.kttk.services.order.util;

import com.kttk.services.order.dto.Cart;
import com.kttk.services.order.dto.CartItem;
import com.kttk.services.order.dto.CartItemResponse;
import com.kttk.services.order.dto.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RestCartService {
    @Autowired
    private RestProductService restProductService;

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    public CartResponse getCartById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        Cart cart = restTemplate.getForObject("http://localhost:8083/api/cart/" + id, Cart.class);
        List<CartItemResponse> cartItemResponses = new ArrayList<CartItemResponse>();
        assert cart != null;
        Collection<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
           CartItemResponse cartItemResponse = new CartItemResponse();
              cartItemResponse.setId(cartItem.getId());
              cartItemResponse.setCartId(id);
              cartItemResponse.setPrice(cartItem.getPrice());
              cartItemResponse.setQuantity(cartItem.getQuantity());
              cartItemResponse.setProductName(cartItem.getProduct().getName());
              cartItemResponses.add(cartItemResponse);
        }
        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());
        cartResponse.setUserId(cart.getUserId());
        cartResponse.setCartItems(cartItemResponses);
        cartResponse.setIsActive(cart.getIsActive());
        cartResponse.setTotalPrice(cart.getTotalPrice());
        cartResponse.setIsPurchased(cart.getIsPurchased());
        cartResponse.setTotalItems(cart.getTotalItems());
        return cartResponse;
    }

}
