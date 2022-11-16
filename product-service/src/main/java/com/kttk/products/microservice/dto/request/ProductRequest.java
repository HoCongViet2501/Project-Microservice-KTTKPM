package com.kttk.products.microservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private String imageUrl;
    private Double price;
    private Integer unitsInStock;
    private Integer categoryId;
    private Integer brandId;
    private Boolean isActive;
}
