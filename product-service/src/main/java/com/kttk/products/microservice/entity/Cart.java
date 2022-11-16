package com.kttk.products.microservice.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private Integer userId;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "total_items")
    private Integer totalItems;
    @Column(name = "is_purchased")
    private Boolean isPurchased;

}