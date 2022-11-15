package com.kttk.products.microservice.repository;

import com.kttk.products.microservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Integer id);

    List<Product> findByBrandId(Integer id);

    List<Product> findByCategoryIdAndBrandId(Integer categoryId, Integer brandId);

    List<Product> findByNameContaining(String keyword);
}