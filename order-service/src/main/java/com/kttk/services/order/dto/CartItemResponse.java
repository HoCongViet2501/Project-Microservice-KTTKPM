package com.kttk.services.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponse {
    private Integer id;
    private Integer cartId;
    private Integer quantity;
    private Double price;
    private String productName;
}
