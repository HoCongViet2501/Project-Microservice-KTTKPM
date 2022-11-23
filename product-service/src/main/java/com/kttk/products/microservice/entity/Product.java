package com.kttk.products.microservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "units_in_stock")
    private Integer unitsInStock;
    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @ManyToOne
//    @JsonIgnore
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

}