package com.kttk.services.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
    private Integer id;
    private Integer userId;
    private Boolean isActive;
    private Double totalPrice;
    private Integer totalItems;
    private Boolean isPurchased;
    private Collection<CartItemResponse> cartItems;
}
