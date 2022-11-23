package com.kttk.services.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private Integer userId;
    private String status;
    private CartResponse cart;
}
