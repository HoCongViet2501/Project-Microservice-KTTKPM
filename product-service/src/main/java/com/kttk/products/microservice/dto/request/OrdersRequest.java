package com.kttk.products.microservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRequest {
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private Integer userId;
    private Integer cartId;
    private String status;
}
