package com.kttk.products.microservice.repository;

import com.kttk.products.microservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

@Repository
@RedisHash("Category")
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}