package com.kttk.products.microservice.repository;

import com.kttk.products.microservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RedisHash("Product")
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(Integer id);

    List<Product> findByBrandId(Integer id);

    List<Product> findByCategoryIdAndBrandId(Integer categoryId, Integer brandId);

    List<Product> findByNameContaining(String keyword);
}