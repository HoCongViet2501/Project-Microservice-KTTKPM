package com.kttk.services.order.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "cart_id", nullable = false)
    private Integer cartId;
    @Column(name = "status", nullable = false)
    private String status;

}